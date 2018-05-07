package com.jt.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.EasyUITreeResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	
	//实现商品分类查询
	@Override
	public List<ItemCat> findItemCatList(Long parentId){
		
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}

	/**
	 * 通用Mapper中的使用方法:
	 * 如果使用对象查询,则根据属性中的非空属性充当where条件
	 */
	@Override
	public List<EasyUITreeResult> findCatListById(Long parentId) {
		List<EasyUITreeResult> treeList = new ArrayList<EasyUITreeResult>();
		//获取商品分类集合信息 调动业务方法
		List<ItemCat> itemCatList = findItemCatList(parentId);	
		for (ItemCat itemCat : itemCatList) {
			EasyUITreeResult result = new EasyUITreeResult();
			result.setId(itemCat.getId());
			result.setText(itemCat.getName());
			//如果是父级则关闭子节点
			String state = itemCat.getIsParent() ? "closed" : "open";
			result.setState(state);
			treeList.add(result);
		}
		return treeList;
	}

}
