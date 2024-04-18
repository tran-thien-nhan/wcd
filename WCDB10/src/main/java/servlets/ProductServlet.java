package servlets;

import dao.ProductDao;
import models.Product;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProducts(req, resp);
        }
        else {
            switch (action) {
                case "create":
                    showFormCreateProduct(req, resp);
                    break;
                case "edit":
                    showFormUpdateProduct(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                case "changeStatus":
                    changeStatus(req, resp);
                    break;
                    //sort product by price asc
                case "sortPriceAsc":
                    sortProductByPriceAsc(req, resp);
                    break;
                    //sort product by price desc
                case "sortPriceDesc":
                    sortProductByPriceDesc(req, resp);
                    break;
                default:
                    getListProducts(req, resp);
                    break;
            }
        }
    }

    private void sortProductByPriceDesc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productDao.sortProductByPriceDesc());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void sortProductByPriceAsc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productDao.sortProductByPriceAsc());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void searchProductByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Product> products = productDao.searchByName(name);
        req.setAttribute("products", products);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void showFormUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Product product = productDao.findProductById(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        productDao.deleteProduct(id);
        resp.sendRedirect("product");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProducts(req, resp);
        }
        else {
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                case "edit":
                    updateProduct(req, resp);
                    break;
                //search by name
                case "search":
                    searchProductByName(req, resp);
                    break;
                    //filter status
                case "filterStatus":
                    filterProductByStatus(req, resp);
                    break;
                default:
                    getListProducts(req, resp);
                    break;
            }
        }
    }

    private void filterProductByStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusParam = req.getParameter("status");
        List<Product> products;

        if (statusParam.equalsIgnoreCase("All")) {
            products = productDao.getListProducts(); // Lấy tất cả sản phẩm
        } else {
            boolean status = Boolean.parseBoolean(statusParam);
            products = productDao.filterByStatus(status);
        }

        req.setAttribute("products", products);
        req.setAttribute("status", statusParam);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }


    private void changeStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        productDao.changeStatus(id);
        resp.sendRedirect("product");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Boolean status = Boolean.parseBoolean(req.getParameter("status"));
        Double price = Double.parseDouble(req.getParameter("price"));
        String fileName = null;

        //get file upload
        Part filePart = req.getPart("image");
        //kiem tra filePart not null
        if (filePart != null && filePart.getSize() > 0) {
            fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            //lay thu muc uploads
            String uploadDir = req.getServletContext().getRealPath("/") + "uploads"; //lay duong dan thu muc uploads
            File directory = new File(uploadDir); //tao file
            if (!directory.exists()) { //kiem tra thu muc uploads da ton tai chua
                directory.mkdirs(); //tao thu muc
            }

            //save file
            String filePath = uploadDir + File.separator + fileName; //lay duong dan file, vd: /uploads/abc.jpg
            try (InputStream fileContent = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(filePath)) {
                //tao mang byte
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            // Nếu không có tệp mới được tải lên, giữ nguyên tên tệp cũ
            fileName = req.getParameter("oldFileName");
        }

        //tao doi tuong product
        Product product = new Product(id, name, price, status, fileName);
        //luu vao database
        productDao.updateProduct(product);

        resp.sendRedirect("product");
    }


    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Boolean status = Boolean.parseBoolean(req.getParameter("status"));
        String price = req.getParameter("price");

        Map<String, String> errors = new HashMap();

        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name is required");
        } else if (name.length() < 5 || name.length() > 20) {
            errors.put("name", "Name must be between 5 and 20 characters");
        } else if (name.matches(".*\\d.*")) {
            errors.put("name", "Name must not contain number");
        }

        double priceValue = 0;
        if (price != null && !price.trim().isEmpty()) {
            try {
                priceValue = Double.parseDouble(price);
                if (priceValue < 10 || priceValue > 400) {
                    errors.put("price", "Price must be between 10 and 400");
                }
            } catch (NumberFormatException e) {
                errors.put("price", "Price is not a valid number");
            }
        } else {
            errors.put("price", "Price is required");
        }

        //validate image
        Part filePart = req.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (!fileName.matches(".*\\.(jpg|png|gif|jpeg)")) {
                errors.put("image", "File must be jpg, png, gif or jpeg");
            }
        } else {
            errors.put("image", "File is required");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("name", name);
            req.setAttribute("price", price);
            req.setAttribute("image", filePart);
            req.getRequestDispatcher("create.jsp").forward(req, resp);
            return;
        }

        //get file upload
        //Part filePart = req.getPart("image");
        //kiem tra filePart not null
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            //lay thu muc uploads
            String uploadDir = req.getServletContext().getRealPath("/") + "uploads"; //lay duong dan thu muc uploads
            File directory = new File(uploadDir); //tao file
            if (!directory.exists()) { //kiem tra thu muc uploads da ton tai chua
                directory.mkdirs(); //tao thu muc
            }

            //save file
            String filePath = uploadDir + File.separator + fileName; //lay duong dan file, vd: /uploads/abc.jpg
            try (InputStream fileContent = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(filePath)) {
                //tao mang byte
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                //tao doi tuong product
                Product product = new Product(name, priceValue, status, fileName);
                //luu vao database
                productDao.createProduct(product);
            }
        } else {
            req.setAttribute("errorFile", "File is required");
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }
        resp.sendRedirect("product");
    }

    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void getListProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDao.getListProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
