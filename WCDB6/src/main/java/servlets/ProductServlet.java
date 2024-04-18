package servlets;

import dao.ProductDao;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet {

    ProductDao dao;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        dao = new ProductDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListPorduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    //create
                    createProduct(req, resp);
                    break;
                case "update":
//                    updateOneProduct(req, resp);
                    break;
                default:
                    getListPorduct(req, resp);
                    break;
            }
        }
    }

//    private void updateOneProduct(HttpServletRequest req, HttpServletResponse resp) {
//        Long id = Long.valueOf(req.getParameter("id"));
//        String name = req.getParameter("name");
//        String description = req.getParameter("description");
//        Double price = Double.parseDouble(req.getParameter("price"));
//        String imageName = req.getParameter("imageName");
//
//        //get file upload
//        Part filePart = null;
//        try {
//            filePart = req.getPart("image");
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }
//        //kiem tra filePart not null
//        if (filePart != null && filePart.getSize() > 0) {
//            //String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            //neu update anh moi thi xoa anh cu
//            if (!imageName.isEmpty()) {
//                String uploadDir = req.getServletContext().getRealPath("/") + "uploads";
//                File file = new File(uploadDir + File.separator + imageName);
//                if (file.exists()) {
//                    file.delete();
//                }
//                //fileName
//                String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//
//            }
//            //neu khong update anh moi thi giu nguyen anh cu
//            else{
//                //fileName
//                String fileName = imageName;
//            }
//
//            //lay thu muc uploads
//            String uploadDir = req.getServletContext().getRealPath("/") + "uploads"; //lay duong dan thu muc uploads
//            File directory = new File(uploadDir); //tao file
//            if (!directory.exists()) { //kiem tra thu muc uploads da ton tai chua
//                directory.mkdirs(); //tao thu muc
//            }
//
//            //save file
//            String filePath = uploadDir + File.separator + fileName; //lay duong dan file, vd: /uploads/abc.jpg
//            try (InputStream fileContent = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(filePath)) {
//                //tao mang byte
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = fileContent.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                //tao doi tuong product
//                Product product = new Product(id, name, description, price, fileName);
//                //luu vao database
//                dao.update(product);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            //tao doi tuong product
//            Product product = new Product(id, name, description, price, imageName);
//            //luu vao database
//            dao.update(product);
//        }
//
//        try {
//            resp.sendRedirect("product");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        //get file upload
        Part filePart = req.getPart("image");
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
                Product product = new Product(name, description,price, fileName);
                //luu vao database
                dao.create(product);
            }
        } else {
            req.setAttribute("errorFile", "File is required");
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }
        resp.sendRedirect("product");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListPorduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    //showformcreate
                    showFormCreate(req, resp);
                    break;
                //delete product
                case "delete":
                    deleteOneProduct(req, resp);
                    break;
                //hien form edit
                case "edit":
                    showFormEdit(req, resp);
                    break;
                default:
                    getListPorduct(req, resp);
                    break;
            }
        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = dao.findById(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    private void deleteOneProduct(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        //tim product theo id
        Product product = dao.findById(id);
        if (product != null) {
            //xoa hinh anh cua product
            String uploadDir = req.getServletContext().getRealPath("/") + "uploads";
            File file = new File(uploadDir + File.separator + product.getImageName());
            if (file.exists()) {
                file.delete();
            }
            //xoa product
            dao.delete(id);
        }

        try {
            resp.sendRedirect("product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void getListPorduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = dao.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
