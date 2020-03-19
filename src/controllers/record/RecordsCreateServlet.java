package controllers.record;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import models.User;
import models.validators.RecordValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsCreateServlet
 */
@WebServlet("/records/create")
public class RecordsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Record r = new Record();

            r.setUser((User)request.getSession().getAttribute("login_user"));

            Date record_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("record_date");
            if(rd_str != null && !rd_str.equals("")) {
                record_date = Date.valueOf(request.getParameter("record_date"));
            }
            r.setUse_date(record_date);

            r.setOverview(request.getParameter("overview"));
            r.setTranzaction(request.getParameter("tranzaction"));


            List<String> errors = RecordValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("record", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/records/index");
            }
        }
    }

}
