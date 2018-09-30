package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.util.DateUtil;
import com.modules.service.FxOrderfxService;
import com.modules.vo.LhyyVo;
import com.modules.vo.LhyyZVo;
import com.modules.pojo.FxOrderfx;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxorderfx")
public class FxOrderfxController extends BaseController {

	private static Logger logger = Logger.getLogger(FxOrderfxController.class);

	@Autowired
	private FxOrderfxService fxOrderfxService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxOrderfx fxOrderfx, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxOrderfx.getId())) {
				fxOrderfxService.save(fxOrderfx);
			} else {
				fxOrderfxService.update(fxOrderfx);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxOrderfx fxOrderfx) {
		try {
			fxOrderfxService.delete(fxOrderfx);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxOrderfx fxOrderfx) {
		ModelAndView modelAndView = new ModelAndView("fxorderfx/info");
		try {
			if (!StringUtils.isEmpty(fxOrderfx.getId())) {
				FxOrderfx data = fxOrderfxService.findObject(fxOrderfx);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxOrderfx fxOrderfx) {
		try {
			FxOrderfx data = fxOrderfxService.findObject(fxOrderfx);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxorderfx/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	@ResponseBody
	public Object plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield,
			String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate) {
		try {
			List<LhyyVo> data = fxOrderfxService.lhyyfx(startdate, enddate, storeid);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "list2")
	public ModelAndView glist2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxorderfx/list2");
		return modelAndView;
	}

	@RequestMapping(value = "yddb")
	@ResponseBody
	public Object sdb(HttpServletRequest request, HttpServletResponse response, String stype, String sfield,
			@DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate) {
		try {
			List<LhyyVo> data = fxOrderfxService.lhyyfx2(startdate, enddate, "");
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "table2")
	@ResponseBody
	public Object plist2(HttpServletRequest request, HttpServletResponse response, String stype, String sfield,
			String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate) {
		try {

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 0);

			List<LhyyVo> data = fxOrderfxService.lhyyfx2(startdate, calendar.getTime(), storeid);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "zotable")
	@ResponseBody
	public ModelAndView atable(HttpServletRequest request, HttpServletResponse response, String storeid,
			String treeid) {
		ModelAndView modelAndView = new ModelAndView("fxorderfx/table");
		try {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
			Date enddate = cal.getTime();
			List<LhyyVo> data1 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 7), enddate, storeid,
					treeid);
			List<LhyyVo> data2 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 14),
					DateUtil.getSpecifiedDayBefore(enddate, 7), storeid, treeid);
			List<LhyyVo> data3 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 21),
					DateUtil.getSpecifiedDayBefore(enddate, 14), storeid, treeid);
			List<LhyyVo> data4 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 28),
					DateUtil.getSpecifiedDayBefore(enddate, 21), storeid, treeid);
			List<LhyyZVo> data = new ArrayList<LhyyZVo>();

			for (LhyyVo vo : data1) {
				LhyyZVo zVo = new LhyyZVo();
				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs1(vo.getZbs());
				zVo.setZbs2(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(vo.getAvdj());
				zVo.setBs1(vo.getBs());
				zVo.setKdj1(vo.getKdj());
				zVo.setXse1(vo.getXse());

				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse2(new BigDecimal(0));
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs2(0);
				zVo.setBs3(0);
				zVo.setBs4(0);

				data.add(zVo);
			}

			loop: for (LhyyVo vo : data2) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj2(vo.getAvdj());
						zVo.setBs2(vo.getBs());
						zVo.setKdj2(vo.getKdj());
						zVo.setXse2(vo.getXse());
						zVo.setZbs2(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs2(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj2(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse2(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj2(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs2(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data3) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj3(vo.getAvdj());
						zVo.setBs3(vo.getBs());
						zVo.setKdj3(vo.getKdj());
						zVo.setZbs3(vo.getZbs());
						zVo.setXse3(vo.getXse());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs3(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj3(vo.getAvdj());
				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse3(vo.getXse());
				zVo.setXse2(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj3(vo.getKdj());
				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs3(vo.getBs());
				zVo.setBs2(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data4) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj4(vo.getAvdj());
						zVo.setBs4(vo.getBs());
						zVo.setKdj4(vo.getKdj());
						zVo.setXse4(vo.getXse());
						zVo.setZbs4(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs4(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs3(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj4(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj2(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse4(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse2(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj4(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj2(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs4(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs2(0);
				data.add(zVo);
			}
			modelAndView.addObject("data", data);
		} catch (

		Exception e) {
			logger.error(e);

		}

		return modelAndView;
	}

	@RequestMapping(value = "zztable")
	@ResponseBody
	public Object aztable(HttpServletRequest request, HttpServletResponse response, String storeid) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
			Date enddate = cal.getTime();
			List<LhyyVo> data1 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 7), enddate, storeid);
			List<LhyyVo> data2 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 14),
					DateUtil.getSpecifiedDayBefore(enddate, 7), storeid);
			List<LhyyVo> data3 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 21),
					DateUtil.getSpecifiedDayBefore(enddate, 14), storeid);
			List<LhyyVo> data4 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedDayBefore(enddate, 28),
					DateUtil.getSpecifiedDayBefore(enddate, 21), storeid);
			List<LhyyZVo> data = new ArrayList<LhyyZVo>();

			for (LhyyVo vo : data1) {
				LhyyZVo zVo = new LhyyZVo();
				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs1(vo.getZbs());
				zVo.setZbs2(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(vo.getAvdj());
				zVo.setBs1(vo.getBs());
				zVo.setKdj1(vo.getKdj());
				zVo.setXse1(vo.getXse());

				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse2(new BigDecimal(0));
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs2(0);
				zVo.setBs3(0);
				zVo.setBs4(0);

				data.add(zVo);
			}

			loop: for (LhyyVo vo : data2) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj2(vo.getAvdj());
						zVo.setBs2(vo.getBs());
						zVo.setKdj2(vo.getKdj());
						zVo.setXse2(vo.getXse());
						zVo.setZbs2(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs2(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj2(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse2(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj2(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs2(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data3) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj3(vo.getAvdj());
						zVo.setBs3(vo.getBs());
						zVo.setKdj3(vo.getKdj());
						zVo.setZbs3(vo.getZbs());
						zVo.setXse3(vo.getXse());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs3(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj3(vo.getAvdj());
				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse3(vo.getXse());
				zVo.setXse2(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj3(vo.getKdj());
				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs3(vo.getBs());
				zVo.setBs2(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data4) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj4(vo.getAvdj());
						zVo.setBs4(vo.getBs());
						zVo.setKdj4(vo.getKdj());
						zVo.setXse4(vo.getXse());
						zVo.setZbs4(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs4(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs3(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj4(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj2(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse4(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse2(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj4(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj2(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs4(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs2(0);
				data.add(zVo);
			}
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "ztable")
	public ModelAndView atable(HttpServletRequest request, HttpServletResponse response, String storeid) {
		ModelAndView modelAndView = new ModelAndView("fxorderfx/table");
		try {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
			Date enddate = cal.getTime();
			List<LhyyVo> data1 = fxOrderfxService.lhyyfx2(DateUtil.getSpecifiedDayBefore(enddate, 7), enddate, storeid);
			List<LhyyVo> data2 = fxOrderfxService.lhyyfx2(DateUtil.getSpecifiedDayBefore(enddate, 14),
					DateUtil.getSpecifiedDayBefore(enddate, 7), storeid);
			List<LhyyVo> data3 = fxOrderfxService.lhyyfx2(DateUtil.getSpecifiedDayBefore(enddate, 21),
					DateUtil.getSpecifiedDayBefore(enddate, 14), storeid);
			List<LhyyVo> data4 = fxOrderfxService.lhyyfx2(DateUtil.getSpecifiedDayBefore(enddate, 28),
					DateUtil.getSpecifiedDayBefore(enddate, 21), storeid);
			List<LhyyZVo> data = new ArrayList<LhyyZVo>();

			for (LhyyVo vo : data1) {
				LhyyZVo zVo = new LhyyZVo();
				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs1(vo.getZbs());
				zVo.setZbs2(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(vo.getAvdj());
				zVo.setBs1(vo.getBs());
				zVo.setKdj1(vo.getKdj());
				zVo.setXse1(vo.getXse());

				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse2(new BigDecimal(0));
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs2(0);
				zVo.setBs3(0);
				zVo.setBs4(0);

				data.add(zVo);
			}

			loop: for (LhyyVo vo : data2) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj2(vo.getAvdj());
						zVo.setBs2(vo.getBs());
						zVo.setKdj2(vo.getKdj());
						zVo.setXse2(vo.getXse());
						zVo.setZbs2(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs2(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj2(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse2(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj2(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs2(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data3) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj3(vo.getAvdj());
						zVo.setBs3(vo.getBs());
						zVo.setKdj3(vo.getKdj());
						zVo.setZbs3(vo.getZbs());
						zVo.setXse3(vo.getXse());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs3(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj3(vo.getAvdj());
				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse3(vo.getXse());
				zVo.setXse2(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj3(vo.getKdj());
				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs3(vo.getBs());
				zVo.setBs2(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data4) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj4(vo.getAvdj());
						zVo.setBs4(vo.getBs());
						zVo.setKdj4(vo.getKdj());
						zVo.setXse4(vo.getXse());
						zVo.setZbs4(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs4(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs3(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj4(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj2(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse4(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse2(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj4(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj2(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs4(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs2(0);
				data.add(zVo);
			}
			modelAndView.addObject("data", data);
		} catch (

		Exception e) {
			logger.error(e);

		}

		return modelAndView;
	}

	@RequestMapping(value = "mtable")
	@ResponseBody
	public Object btable(HttpServletRequest request, HttpServletResponse response, String stype, String sfield,
			String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate) {
		try {
			List<LhyyVo> data = fxOrderfxService.lhyyfx(startdate, enddate, storeid);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "zmtable")
	@ResponseBody
	public Object amtable(HttpServletRequest request, HttpServletResponse response, String storeid) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date enddate = cal.getTime();
			List<LhyyVo> data1 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedMonthAfter(enddate, -1), enddate,
					storeid);
			List<LhyyVo> data2 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedMonthAfter(enddate, -2),
					DateUtil.getSpecifiedMonthAfter(enddate, -1), storeid);
			List<LhyyVo> data3 = fxOrderfxService.lhyyfx(DateUtil.getSpecifiedMonthAfter(enddate, -3),
					DateUtil.getSpecifiedMonthAfter(enddate, -4), storeid);
			List<LhyyZVo> data = new ArrayList<LhyyZVo>();

			for (LhyyVo vo : data1) {
				LhyyZVo zVo = new LhyyZVo();
				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs1(vo.getZbs());
				zVo.setZbs2(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(vo.getAvdj());
				zVo.setBs1(vo.getBs());
				zVo.setKdj1(vo.getKdj());
				zVo.setXse1(vo.getXse());

				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse2(new BigDecimal(0));
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs2(0);
				zVo.setBs3(0);
				zVo.setBs4(0);

				data.add(zVo);
			}

			loop: for (LhyyVo vo : data2) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj2(vo.getAvdj());
						zVo.setBs2(vo.getBs());
						zVo.setKdj2(vo.getKdj());
						zVo.setXse2(vo.getXse());
						zVo.setZbs2(vo.getZbs());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs2(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs3(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj2(vo.getAvdj());
				zVo.setAvdj3(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse2(vo.getXse());
				zVo.setXse3(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj2(vo.getKdj());
				zVo.setKdj3(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs2(vo.getBs());
				zVo.setBs3(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			loop: for (LhyyVo vo : data3) {

				for (LhyyZVo zVo : data) {
					if (zVo.getTreeid().equals(vo.getTreeid())) {
						zVo.setAvdj3(vo.getAvdj());
						zVo.setBs3(vo.getBs());
						zVo.setKdj3(vo.getKdj());
						zVo.setZbs3(vo.getZbs());
						zVo.setXse3(vo.getXse());
						continue loop;
					}
				}
				LhyyZVo zVo = new LhyyZVo();

				zVo.setTreeid(vo.getTreeid());
				zVo.setLv1(vo.getLv1());
				zVo.setLv2(vo.getLv2());

				zVo.setZbs3(vo.getZbs());
				zVo.setZbs1(0);
				zVo.setZbs2(0);
				zVo.setZbs4(0);

				zVo.setAvdj1(new BigDecimal(0));
				zVo.setAvdj3(vo.getAvdj());
				zVo.setAvdj2(new BigDecimal(0));
				zVo.setAvdj4(new BigDecimal(0));

				zVo.setXse1(new BigDecimal(0));
				zVo.setXse3(vo.getXse());
				zVo.setXse2(new BigDecimal(0));
				zVo.setXse4(new BigDecimal(0));

				zVo.setKdj1(new BigDecimal(0));
				zVo.setKdj3(vo.getKdj());
				zVo.setKdj2(new BigDecimal(0));
				zVo.setKdj4(new BigDecimal(0));

				zVo.setBs1(0);
				zVo.setBs3(vo.getBs());
				zVo.setBs2(0);
				zVo.setBs4(0);
				data.add(zVo);
			}

			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

}