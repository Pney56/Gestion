package fr.school.controller;

import fr.school.dao.EtudiantDAO;
import fr.school.dao.EtudiantDAOImpl;
import fr.school.dao.InscriptionDAO;
import fr.school.dao.InscriptionDAOImpl;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/etudiant/*")
public class EtudiantController extends HttpServlet {

    private EtudiantDAO etudiantDAO;
    private InscriptionDAO inscriptionDAO;

    public void init() {
        etudiantDAO = new EtudiantDAOImpl();
        inscriptionDAO = new InscriptionDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/list")) {
            listEtudiants(request, response);
        } else if (action.equals("/details")) {
            detailsEtudiant(request, response);
        } else if (action.equals("/formulaire")) {
            formulaireEtudiant(request, response);
        } else if (action.equals("/delete")) {
            supprimerEtudiant(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> listeEtudiants = etudiantDAO.findAll();

        // Ajoutez ces instructions de débogage
        for (Etudiant etudiant : listeEtudiants) {
            System.out.println("ID: " + etudiant.getId() + ", Nom: " + etudiant.getNom() + ", Prénom: " + etudiant.getPrenom());
        }

        request.setAttribute("listeEtudiants", listeEtudiants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listeEtudiants.jsp");
        dispatcher.forward(request, response);
    }



    private void detailsEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Etudiant etudiant = etudiantDAO.findById(id);
                if (etudiant != null) {
                    List<Inscription> inscriptions = inscriptionDAO.findInscriptionsByStudentId(id);
                    request.setAttribute("etudiant", etudiant);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detailsEtudiant.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Étudiant avec l'ID " + id + " non trouvé.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'étudiant invalide.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'étudiant manquant dans la requête.");
        }
    }



    private void formulaireEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Etudiant etudiant = etudiantDAO.findById(id);
                request.setAttribute("etudiant", etudiant);
            } catch (NumberFormatException e) {
                // Gestion de l'exception
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulaireEtudiant.jsp");
        dispatcher.forward(request, response);
    }

    private void supprimerEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                etudiantDAO.delete(id);
                response.sendRedirect(request.getContextPath() + "/etudiant/list");
            } catch (NumberFormatException e) {
                // Gestion de l'exception
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'étudiant manquant dans la requête.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        if (nom != null && prenom != null) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(nom);
            etudiant.setPrenom(prenom);

            if (idParam != null && !idParam.isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    etudiant.setId(id);
                    etudiantDAO.update(etudiant);
                } catch (NumberFormatException e) {
                    // Gestion de l'exception
                }
            } else {
                etudiantDAO.insert(etudiant);
            }
            response.sendRedirect(request.getContextPath() + "/etudiant/list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nom et/ou prénom de l'étudiant manquant(s) dans la requête.");
        }
    }
}
