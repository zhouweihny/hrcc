package com.commons.util.excel;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = -3434719712955859295L;

	private Long id;
	@ModelProp(name = "电话", colIndex = 1, nullable = false)
	private String telephone;

	@ModelProp(name = "名称", colIndex = 0, nullable = false)
	private String name;

	@ModelProp(name = "性别", colIndex = 2, nullable = false)
	private Integer sex;
}