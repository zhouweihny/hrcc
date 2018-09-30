package com.commons.util.fx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.modules.pojo.FxTree;
import com.modules.vo.FxDrugType;
import com.modules.vo.drugFX.FxDrugTypeItemVo;

public  class FxUtil {

	public static String getTreeName(List<FxTree> list, String treeid){		


		for(FxTree tree : list){
			if(treeid.equals(tree.getId()))
			{
				return tree.getName();
			}
		}

		return null;
	}

	/*
	 * 获取总金额 和 分类总金额
	 */
	public static BigDecimal  getTotalAmt(List<FxDrugType> list,String treeid){

		BigDecimal totalAmt=new BigDecimal(0);
		for(FxDrugType info : list){
			if(treeid.isEmpty()){
				totalAmt =totalAmt.add(info.getQty().multiply(info.getPrice()));

			}
			else{

				if(treeid.equals(info.getTreeid()))
					totalAmt =totalAmt.add(info.getQty().multiply(info.getPrice()));
			}

		}

		return totalAmt;
	}

	/*
	 * 获取分类品种数
	 */
	public static int getdrugNum(List<FxDrugType> list,String treeid){

		int count=0;
		String drugid="";
		for(FxDrugType info : list){
			if(treeid.isEmpty()){				
				count = count + 1;	

			}else{

				if(treeid.equals(info.getTreeid()))
				{
					if(drugid.isEmpty() || !drugid.equals(info.getId())){
						count = count + 1;
					}
				}
			}
			
			drugid = info.getId();
			
		}

		return count;
	}


	/*
	 * 获取分类品种数
	 */
	public static BigDecimal getAvgdrugNumPrice(List<FxDrugType> list,String treeid){

		BigDecimal totalPrice=new BigDecimal(0);
		int count=0;
		String drugid="";
		for(FxDrugType info : list){
			if(treeid.isEmpty()){				
				//count = count + 1;	

			}else{

				if(treeid.equals(info.getTreeid()))
				{
					//if(drugid.isEmpty() || !drugid.equals(info.getId())){
						count = count + 1;
				//		System.out.println(info.getCode() + "   " + info.getName() + "    " + info.getPrice());
						totalPrice = totalPrice.add(info.getPrice());
				//	}
				}
			}
			//drugid=info.getId();
		}

		if(count > 0){

			totalPrice = totalPrice.divide(new BigDecimal(count),4,BigDecimal.ROUND_HALF_UP);
		}
		return totalPrice;
	}

	public static BigDecimal getMaxPrice(List<FxDrugType> list,String treeid){

		BigDecimal price=new BigDecimal(0);
		for(FxDrugType info : list){


			if(treeid.equals(info.getTreeid()))
			{


				if( price.compareTo(info.getPrice()) < 0)
				{
					price = info.getPrice();
				}

			}
		}



		return price;
	}

	public static BigDecimal getMinPrice(List<FxDrugType> list,String treeid){

		BigDecimal price=new BigDecimal(0);
		for(FxDrugType info : list){


			if(treeid.equals(info.getTreeid()))
			{

				if( price.compareTo(new BigDecimal(0)) == 0)
				{
					price = info.getPrice();
				}else  if( price.compareTo(info.getPrice()) > 0)
				{
					price = info.getPrice();
				}

			}
		}



		return price;
	}


	/*
	 * 获取分类平均毛利利率
	 */
	public static BigDecimal  getFluentRate(List<FxDrugType> list,String treeid){

		BigDecimal totalFluentRate=new BigDecimal(0);

		String drugid="";
		int count=0;

		for(FxDrugType info : list){

			if(treeid.equals(info.getTreeid())){

				if(drugid.isEmpty() || !drugid.equals(info.getId())){
					count = count + 1;
					if(info.getCostprice()!=null)
					totalFluentRate =totalFluentRate.add(info.getPrice().subtract(info.getCostprice()).divide(info.getPrice(),4,BigDecimal.ROUND_DOWN));
				}
				
				drugid=info.getId();

			}

		}

		if(count>0)
		return totalFluentRate.divide(new BigDecimal(count),4,BigDecimal.ROUND_HALF_DOWN);
		else
			return new BigDecimal(0);
	}
	
	
	/*
	 * 获取分类数量
	 */
	public static BigDecimal getTotalQty(List<FxDrugType> list,String treeid){

		BigDecimal totalqty=new BigDecimal(0);
		
		for(FxDrugType info : list){
			if(treeid.isEmpty()){				
				totalqty = totalqty.add(info.getQty());	

			}else{

				if(treeid.equals(info.getTreeid()))
				{
					totalqty = totalqty.add(info.getQty());	
				}
			}
			
		}

		
		return totalqty;
	}

	public static List<FxDrugTypeItemVo> getFxDrugTypeItemVoList(List<FxDrugType> salList, String treeid) {
		// TODO Auto-generated method stub
		
		List<FxDrugTypeItemVo> items = new ArrayList<FxDrugTypeItemVo>();
		for(FxDrugType info : salList){
			

				if(treeid.equals(info.getTreeid()))
				{
					FxDrugTypeItemVo item =new FxDrugTypeItemVo();
					
					item.setCode(info.getCode());
					item.setName(info.getName());
					item.setSpecifications(info.getSpecifications());
					item.setUnit(info.getUnit());
					item.setFactory(info.getFactory());
					item.setQty(info.getQty());
					item.setCostprice(info.getCostprice());
					item.setPrice(info.getPrice());
					items.add(item);
				}
		
		}

		
		
		
		return items;
	}
	
	public static List<FxTree>  getFxTreeList(List<FxTree> datas,int levle){
		List<FxTree> list = new  ArrayList<FxTree>();
		
		for(FxTree info : datas)
		{
			int length=0;
			
			if(info.getPath().indexOf("/")>-1)
			{
				length = info.getPath().split("/").length;
			}else{
				length=1;
			}
			if(levle==length){
				
				list.add(info);

			}
			
		}
		return list;
	}
	
	public static List<FxTree>  getFxTreeList(List<FxTree> datas,String parentid){
		List<FxTree> list = new  ArrayList<FxTree>();
		
		for(FxTree info : datas)
		{
			if(parentid.equals(info.getParentid())){
				list.add(info);
			}
			
		}
		return list;
	}
	
	
	public static int  getcomnamenum(List<FxDrugType> datas,String treeid){
		int count=0;
		
		for(FxDrugType info : datas)
		{
			if(treeid.equals(info.getTreeid())){
				count = count+1;
			}
			
		}
		return count;
	}
	
}
