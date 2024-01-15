package fr.school.controller;

import fr.school.dao.*;
import fr.school.modele.Cours;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet({"/cours/*"})
public class CoursController extends HttpServlet {

    private CoursDAO coursDAO;

    private EtudiantDAO etudiantDAO;

    private InscriptionDAO inscriptionDAO;

    public void init() {
        coursDAO = new CoursDAOImpl();
        etudiantDAO = new EtudiantDAOImpl();
        inscriptionDAO = new InscriptionDAOImpl();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/list")) {
            listCours(request, response);
        } else if (action.equals("/details")) {
            // Logique pour afficher les détails d'un cours
            String code = request.getParameter("code");
            if (code != null) {
                Cours cours = coursDAO.findByCode(code);
                if (cours != null) {
                    request.setAttribute("cours", cours);
                    List<Inscription> inscriptions = inscriptionDAO.findInscriptionsByCourseCode(code);
                    request.setAttribute("inscriptions", inscriptions);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detailsCours.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } else if (action.equals("/formulaire")) {
            formulaireCours(request, response);
        } else if (action.equals("/delete")) {
            // Logique pour la suppression d'un cours
            String code = request.getParameter("code");
            if (code != null) {
                coursDAO.delete(code);
                response.sendRedirect(request.getContextPath() + "/cours/list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Code du cours manquant dans la requête.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }





    private void listCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cours> listCours = coursDAO.findAll();
        request.setAttribute("listCours", listCours);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listeCours.jsp");
        dispatcher.forward(request, response);
    }

    private void detailsCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            Cours cours = coursDAO.findByCode(code);
            if (cours != null) {
                request.setAttribute("cours", cours);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detailsCours.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cours avec le code " + code + " n'est pas trouvé.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Code du cours manquant dans la requête.");
        }
    }



    private void formulaireCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Cours cours = null;
        if (code != null) {
            cours = coursDAO.findByCode(code);
        }
        request.setAttribute("cours", cours);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulaireCours.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String libelle = request.getParameter("libelle");

        Cours cours = new Cours(code, libelle);
        if (coursDAO.findByCode(code) != null) {
            // Si le cours existe déjà, mettez-le à jour
            coursDAO.update(cours);
        } else {
            // Sinon, ajoutez un nouveau cours
            coursDAO.insert(cours);
        }
        response.sendRedirect(request.getContextPath() + "/cours/list");
    }


}
