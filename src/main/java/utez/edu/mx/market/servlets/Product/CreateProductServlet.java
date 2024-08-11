package utez.edu.mx.market.servlets.Product;

import utez.edu.mx.market.daos.ProductDao;
import utez.edu.mx.market.entities.Category;
import utez.edu.mx.market.entities.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateProductServlet", value = "/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ProductDao daoProduct = new ProductDao();
        String name = request.getParameter("name");
        long stock = Long.parseLong(request.getParameter("stock"));
        int idCategory = Integer.parseInt(request.getParameter("category"));
        String description = request.getParameter("description");

        Product product = new Product(name, description, stock, true, new Category(idCategory, null));
        request.setAttribute("success", daoProduct.createProduct(product));

        doGet(request, response);
    }
}