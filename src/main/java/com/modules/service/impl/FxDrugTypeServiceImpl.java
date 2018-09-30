package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxDrugTypeService;
import com.modules.dao.FxDrugTypeDao;
import com.modules.vo.FxDrugType;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxDrugTypeServiceImpl implements FxDrugTypeService {
	@Autowired
	private FxDrugTypeDao fxDrugTypeDao;
	
	public void save(FxDrugType entity) throws Exception {
		fxDrugTypeDao.save(entity);
	}

	public void save(List<FxDrugType> entities) throws Exception {
		fxDrugTypeDao.save(entities);
	}

	public void update(FxDrugType entity) throws Exception {
		fxDrugTypeDao.update(entity);
	}

	public void update(FxDrugType newEntity, FxDrugType oldEntity) throws Exception {
		fxDrugTypeDao.update(newEntity, oldEntity);
	}

	public void delete(FxDrugType entity) throws Exception {
		fxDrugTypeDao.delete(entity);
	}

	public FxDrugType findObject(FxDrugType entity) throws Exception {
		return fxDrugTypeDao.findObject(entity);
	}

	public List<FxDrugType> findList(FxDrugType entity) throws Exception {
		return fxDrugTypeDao.findList(entity);
	}

	public PageResult<FxDrugType> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" SELECT a.`code`,a.id drugid,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path,'' treename1,'' treename2,'' treename3,'' treename4 ");
		sql.append(" FROM t_drug a,fx_comname b,fx_comname_tree e, fx_tree c  where a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id   and a.comnameid is not null  ");
		
		if(params.get("catalogid") !=null ){
			
			sql.append("  and a.catalogid='"+ params.get("catalogid") +"' ");
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxDrugTypeDao.findList(sqlContext, page, FxDrugType.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.modules.service.FxDrugTypeService#findList(java.util.Map)
	 * 查询销售品种目录数据
	 */
	@Override
	public List<FxDrugType> findList(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.`code`,a.id id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path,'' treename1,'' treename2,'' treename3,'' treename4,sum(d.qty) as qty,d.price,d.costprice ");
		sql.append(" FROM fx_impsaledata d,t_drug a,fx_comname b, fx_comname_tree e,fx_tree c  where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id  and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0 ");

		//		sql.append(" SELECT a.`code`,a.id id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path,'' treename1,'' treename2,'' treename3,'' treename4,d.qty as qty,d.price,d.costprice ");
		//		sql.append(" FROM ( select drugid , sum(qty) as qty,price,costprice  from fx_impsaledata where  qty>0 and price>0 and  costprice > 0 group by drugid ) d,t_drug a,fx_comname b, fx_tree c  where d.drugid = a.id and a.comnameid = b.id and  b.treeid =c.id  and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0 ");

		if(params.get("storeid") !=null  && params.get("storeid").toString().length()>0 )
		{
			sql.append(" and  d.storeid='"+ params.get("storeid") +"' ");
		}
		if(params.get("catalogid") !=null ){

			sql.append(" and  a.catalogid='"+ params.get("catalogid") +"' ");
		}
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0)
		{
			sql.append(" and c.path like '%"+ params.get("treeid") +"%' ");
		}
		
		
		
		if(params.get("impfilenameid") !=null  ){
			sql.append(" and d.impfilenameid='"+ params.get("impfilenameid") +"' ");
		}
		

		sql.append(" group by a.`code`,a.id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name`,b.id,c.id,c.`name`,c.path,d.price,d.costprice ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}else{

			sql.append(" order by ").append(" c.path,a.id ");
		}
		sqlContext.setSql(sql.toString());
		//sqlContext.setParams(ps.toArray());
		List<FxDrugType> list =  fxDrugTypeDao.findList(sqlContext, FxDrugType.class);

		//		 FxTree tree=new FxTree();
		//		 List<FxTree> data = fxTreeService.findList(tree);
		//			
		//		 for(FxDrugType info : list){
		//			 
		//			 String id[]=info.getPath().split("/");
		//			 for(int i=0; i<id.length;i++){
		//				 
		//				String treename = FxUtil.getTreeName(data,id[i]);
		//				 if(i==0)
		//					 info.setTreename1(treename);
		//				 if(i==1)
		//					 info.setTreename2(treename);
		//				 if(i==2)
		//					 info.setTreename3(treename);
		//				 if(i==3)
		//					 info.setTreename4(treename);				
		//			 }
		//		 }
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.modules.service.FxDrugTypeService#findList(java.util.Map)
	 * 查询品种目录数据
	 */
	@Override
	public List<FxDrugType> findAllList(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
	//	List<Object> ps = new ArrayList<Object>();
		sql.append(" SELECT a.`code`,a.id id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path,'' treename1,'' treename2,'' treename3,'' treename4 ");
		
		sql.append(" FROM t_drug a,fx_comname b, fx_comname_tree e,fx_tree c  where  a.comnameid = b.id  and b.id=e.comnameid and e.treeid=c.id  and a.comnameid is not null   ");
		
		if(params.get("catalogid") !=null ){
			
			sql.append(" and  a.catalogid='"+ params.get("catalogid") +"' ");
		}
//		if(params.get("storeid") !=null  && params.get("storeid").toString().length()>0 )
//		{
//			sql.append(" and  d.storeid='"+ params.get("storeid") +"' ");
//		}
		
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0)
		{
			sql.append(" and c.path like '%"+ params.get("treeid") +"%' ");
		}
		
			
		sql.append(" group by a.`code`,a.id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name`,b.id,c.id,c.`name`,c.path ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}else{
			
			sql.append(" order by ").append(" c.id,a.id ");
		}
		sqlContext.setSql(sql.toString());
		//sqlContext.setParams(ps.toArray());
		 List<FxDrugType> list =  fxDrugTypeDao.findList(sqlContext, FxDrugType.class);
		 
//		 FxTree tree=new FxTree();
//		 List<FxTree> data = fxTreeService.findList(tree);
//			
//		 for(FxDrugType info : list){
//			 
//			 String id[]=info.getPath().split("/");
//			 for(int i=0; i<id.length;i++){
//				 
//				String treename = FxUtil.getTreeName(data,id[i]);
//				 if(i==0)
//					 info.setTreename1(treename);
//				 if(i==1)
//					 info.setTreename2(treename);
//				 if(i==2)
//					 info.setTreename3(treename);
//				 if(i==3)
//					 info.setTreename4(treename);				
//			 }
//		 }
		return list;
	}

	@Override
	public List<FxDrugType> findStoreList(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT a.`code`,a.id id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path,'' treename1,'' treename2,'' treename3,'' treename4,sum(d.qty) as qty,avg(d.costprice) as costprice  ");
		
		sql.append(" FROM fx_impstore d,t_drug a,fx_comname b, fx_comname_tree e,fx_tree c  where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id   and a.comnameid is not null and  d.qty>0 and  d.costprice>0 ");
		
//		if(params.get("catalogid") !=null ){
//			
//			sql.append(" and  a.catalogid='"+ params.get("catalogid") +"' ");
//		}
		
		if(params.get("impfilenameid") !=null && params.get("impfilenameid").toString().length()>0  )
		{
			sql.append(" and  d.impfilenameid='"+ params.get("impfilenameid") +"' ");
		}
		if(params.get("id") !=null && params.get("id").toString().length()>0  ){
			
			sql.append(" and  d.id='"+ params.get("id") +"' ");
		}
		
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0 )
		{
			sql.append("  and c.path like '%"+ params.get("treeid") +"%' ");
		}
		sql.append(" group by a.`code`,a.id,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,b.`name`,b.id,c.id,c.`name`,c.path");

		sql.append(" order by ").append(" c.path,a.id ");
		sqlContext.setSql(sql.toString());
	
		 List<FxDrugType> list =  fxDrugTypeDao.findList(sqlContext, FxDrugType.class);
	
		return list;
	}

	@Override
	public List<FxDrugType> findDrugTypeSaleLevel(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
	
		
		sql.append(" select * from ( ");
		sql.append(" SELECT c.`name` treename,c.path, ");
		sql.append(" sum(d.qty *d.price) as price ");
		sql.append("  FROM fx_impsaledata d, ");
		sql.append(" t_drug a,fx_comname b,fx_comname_tree e, fx_tree c   ");
		sql.append(" where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id    ");
		sql.append(" and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0  ");

		if(params.get("catalogid") !=null ){

			sql.append("  and a.catalogid='"+ params.get("catalogid") +"' ");
		}
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0 )
		{
			sql.append("  and c.path like '%"+ params.get("treeid") +"%' ");
		}	
		sql.append(" GROUP BY c.`name`,c.path  ");
		sql.append(" ) a order by amt desc ");
		
		
		sqlContext.setSql(sql.toString());
		
		return fxDrugTypeDao.findList(sqlContext,  FxDrugType.class);
	}

	@Override
	public List<FxDrugType> findDrugTypeSaleDrugnum(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
	
		
		sql.append(" select * from (  ");
		sql.append(" SELECT c.`name` treename,c.path, count(d.drugid) as flevel  ");
		sql.append(" FROM fx_impsaledata d,  ");
		sql.append(" t_drug a,fx_comname b, fx_comname_tree e,fx_tree c    ");
		sql.append(" where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id     ");
		sql.append(" and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0   ");		

		if(params.get("catalogid") !=null ){

			sql.append("  and a.catalogid='"+ params.get("catalogid") +"' ");
		}
				
		
		if(params.get("userid") !=null ){

			sql.append("  and d.userid='"+ params.get("userid") +"' ");
		}
		
		if(params.get("impfilenameid") !=null  && params.get("impfilenameid").toString().length()>0 ){

			sql.append("  and d.impfilenameid='"+ params.get("impfilenameid") +"' ");
		}		
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0 )
		{
			sql.append("  and c.path like '%"+ params.get("treeid") +"%' ");
		}
		
		sql.append(" GROUP BY c.`name`,c.path   ");
		sql.append(" ) a order by flevel desc ");
		
		
		sqlContext.setSql(sql.toString());
		
		return fxDrugTypeDao.findList(sqlContext,  FxDrugType.class);
	}

	@Override
	public List<FxDrugType> findDrugTypeSaleml(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();

		sql.append(" select treename,path,price,qty from ( ");
		sql.append(" SELECT c.`name` treename,c.path, sum(d.qty *d.price) as price,(sum(d.qty *d.price)-sum(d.qty *d.costprice))/sum(d.qty *d.price) as qty ");
		sql.append("  FROM fx_impsaledata d, ");
		sql.append(" t_drug a,fx_comname b, fx_comname_tree e,fx_tree c   ");
		sql.append(" where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id    ");
		sql.append(" and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0  ");

		if(params.get("catalogid") !=null ){

			sql.append("  and a.catalogid='"+ params.get("catalogid") +"' ");
		}

		if(params.get("userid") !=null ){

			sql.append("  and d.userid='"+ params.get("userid") +"' ");
		}
		
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0 )
		{
			sql.append("  and c.path like '%"+ params.get("treeid") +"%' ");
		}
		
		if(params.get("impfilenameid") !=null && params.get("impfilenameid").toString().length()>0 ){

			sql.append("  and d.impfilenameid='"+ params.get("impfilenameid") +"' ");
		}
		
		
		sql.append(" GROUP BY c.`name`,c.path  ");
		sql.append(" ) a   order by qty,price desc ");

		sqlContext.setSql(sql.toString());

		return fxDrugTypeDao.findList(sqlContext,  FxDrugType.class);
	}

	@Override
	public List<FxDrugType> findDrugTypecomname(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  b.`name` comname,b.id comnameid,c.id treeid,c.`name` treename,c.path ");
		sql.append(" FROM  ");
		sql.append("  fx_impsaledata  d, ");
		sql.append(" t_drug a,fx_comname b, fx_comname_tree e,fx_tree c  where d.drugid = a.id and a.comnameid = b.id and b.id=e.comnameid and e.treeid=c.id     ");
		sql.append(" and a.comnameid is not null and d.qty>0 and d.price>0 and  d.costprice > 0  ");

		if(params.get("catalogid") !=null ){

			sql.append("  and a.catalogid='"+ params.get("catalogid") +"' ");
		}

		if(params.get("userid") !=null ){

			sql.append("  and d.userid='"+ params.get("userid") +"' ");
		}
		
		if(params.get("treeid") !=null && params.get("treeid").toString().length()>0 )
		{
			sql.append("  and c.path like '%"+ params.get("treeid") +"%' ");
		}
		
		if(params.get("impfilenameid") !=null  && params.get("impfilenameid").toString().length()>0 ){

			sql.append("  and d.impfilenameid='"+ params.get("impfilenameid") +"' ");
		}
		
		
		sql.append(" group by b.`name`,b.id,c.id,c.`name`,c.path ");
		sql.append(" order by  c.path ");
		sqlContext.setSql(sql.toString());

		return fxDrugTypeDao.findList(sqlContext,  FxDrugType.class);
	}



}
