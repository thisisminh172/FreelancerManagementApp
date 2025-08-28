/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DuAnDAO;
import java.util.List;
import java.util.Map;
import model.DuAn;

/**
 *
 * @author thisi
 */
public class DuAnController {
    private DuAnDAO duAnDAO;

    public DuAnController() {
        duAnDAO = new DuAnDAO();
    }

    public int insert(DuAn da) {
        return duAnDAO.insert(da);
    }

    public int delete(int maDuAn) {
        return duAnDAO.delete(maDuAn);
    }

    public int update(DuAn da) {
        return duAnDAO.update(da);
    }

    public List<Map<String, Object>> getAll() {
        return duAnDAO.getAll();
    }

    public DuAn findById(int id) {
        return duAnDAO.findById(id);
    }

    public int clearDuAnFreelancerByDuAnId(int duanId) {
        return duAnDAO.clearDuAnFreelancerByDuAnId(duanId);
    }

    public int insertDuAnFreelancer(int duanId, int freelancerId) {
        return duAnDAO.insertDuAnFreelancer(duanId, freelancerId);
    }
}
