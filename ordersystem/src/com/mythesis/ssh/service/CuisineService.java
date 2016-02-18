package com.mythesis.ssh.service;

import java.util.List;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.Cuisine;

public interface CuisineService extends BaseDao<Cuisine> {

	List<Cuisine> findFiveCuisines(String now);

	List<Cuisine> findAllOrderCuisines();

}
