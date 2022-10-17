package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phi Nguyen
 */
public class ShoppingList extends HttpServlet {

    ArrayList<String> items;
    /**
     * Constructor
     */
    public ShoppingList() {
        this.items = new ArrayList<>();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse respond) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");

        if (action != null && action.equals("logout")) {
            //clear current session and reset
            items.clear();
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, respond);           
        }
        if (session.getAttribute("username") == null) 
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, respond);
        else 
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/shoppingList.jsp").forward(request, respond);              
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse respond) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String item = request.getParameter("item");
        String[] userItems = request.getParameterValues("userItems");
        
        try{
            switch (action) {
                case "register":
                    session.setAttribute("username", username);
                    break;
                case "add":
                    items.add(item);
                    session.setAttribute("itemList", items);
                    break;
                case "delete":
                    for (String userItem : userItems) {
                        for (int j = 0; j < items.size(); j++) {
                            if (userItem.equals(items.get(j))) {
                                items.remove(j);
                                session.setAttribute("itemList", items);
                            }
                        }
                    }       
                    break;
            }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/shoppingList.jsp").forward(request, respond);
        }
        catch(NullPointerException e){
            request.setAttribute("message", "No items found.");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/shoppingList.jsp").forward(request, respond);
        }
    }
}
