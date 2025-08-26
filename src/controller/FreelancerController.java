package controller;

import dao.FreelancerDAO;
import model.Freelancer;

import java.util.List;

public class FreelancerController {

    private FreelancerDAO freelancerDAO;

    public FreelancerController() {
        freelancerDAO = new FreelancerDAO();
    }

    public int insert(Freelancer f) {
        return freelancerDAO.insert(f);
    }

    public int delete(int maFreelancer) {
        return freelancerDAO.delete(maFreelancer);
    }

    public int update(Freelancer f) {
        return freelancerDAO.update(f);
    }

    public List<Freelancer> getAll() {
        return freelancerDAO.getAll();
    }

    public Freelancer findById(String id) {
        return freelancerDAO.findById(id);
    }
}
