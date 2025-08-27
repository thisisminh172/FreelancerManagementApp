package controller;

import dao.FreelancerDAO;
import model.Freelancer;

import java.util.List;
import java.util.Map;
import model.KyNang;

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

    public List<Map<String, Object>> getAll() {
        return freelancerDAO.getAll();
    }

    public Freelancer findById(String id) {
        return freelancerDAO.findById(id);
    }
    
    public List<KyNang> getFreelancerKyNangById(int id) {
        return freelancerDAO.getFreelancerKyNangById(id);
    }

    public int deleteAllFreelancerKyNangById(int id) {
        return freelancerDAO.deleteAllFreelancerKyNangById(id);
    }

    public int insertFreelancerKyNang(int id, int kyNangId) {
        return freelancerDAO.insertFreelancerKyNang(id, kyNangId);
    }
}
