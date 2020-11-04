package com.yan.goods.delivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.yan.goods.delivery.domain.Delivery;

@Service
public class DeliveryService {
	
	private final static Logger log = LoggerFactory.getLogger(DeliveryService.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveDelivary(Delivery de) {
		mongoTemplate.insert(de, "delivery");
	}

	public List<Delivery> findDeliveryByName(String sendName) {
		Query query = new Query(Criteria.where("sendName").is(sendName));
		// Delivery mgt = mongoTemplate.findOne(query, Delivery.class);
		List<Delivery> mgt = mongoTemplate.find(query, Delivery.class);
		return mgt;
	}

	/**
	 * 更新对象
	 */
	public void updateDelivery(String id, String aname) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("arrivalName", aname);
		// 更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query, update, Delivery.class);
		// 更新查询返回结果集的所有
		// mongoTemplate.updateMulti(query,update,TestEntity.class);
	}

	public void removeDelivery(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, Delivery.class);
	}

	public List<Delivery> selectPage(Integer currentPage, Integer pageSize, String sendName) {
		// 创建查询对象
		Query query = new Query();
		// 设置起始数
		query.skip((currentPage - 1) * pageSize);
		// 设置查询条数
		query.limit(pageSize);
		Criteria criteria = new Criteria();
		criteria.where("sendName").is(sendName);
		query.addCriteria(criteria);
		// 查询当前页数据集合
		List<Delivery> list = mongoTemplate.find(query, Delivery.class);
		// 查询总记录数
		int count = (int) mongoTemplate.count(query, Delivery.class);
		int total = count % pageSize == 0 ? count : count / pageSize + 1;
		log.info("count:"+count+",total:"+total);
		// 创建分页实体对象
//		List<Delivery> page = new ArrayList<>();
		// 添加每页的集合、数据总条数、总页数
//		page.setRecords(ApplyLogList);
//		page.setSize(count);
//		page.setTotal(count % pageSize == 0 ? 1 : count / pageSize + 1);
		return list;
	}
}
