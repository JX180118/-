package com.soft.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.bean.TbCar;
import com.soft.bean.TbParkNum;
import com.soft.bean.TbParkPlace;
import com.soft.bean.TbRule;
import com.soft.bean.ViewCarPark;
import com.soft.biz.CarParkCheckBiz;
import com.soft.biz.ParkModifyBiz;

@Controller
@RequestMapping("/modifyParkPlace")
public class ModifyParkHandler {
	@Resource
	private ParkModifyBiz ParkModifyBizImpl;
	 
	@Resource
	private TbParkPlace ParkPlace;
	 

	@RequestMapping("/inIt.action")
	public String carpark(HttpServletRequest request) {
		long parkNum = ParkModifyBizImpl.findAllNum();
		request.setAttribute("parkNum", parkNum);
		return "manage/modifyPark";
	}

	// 跳转修改车位编号
	@RequestMapping("/modify.action")
	public String modify(HttpServletRequest request, TbParkNum parkNum) {
		String prifix1 = parkNum.getPrifix1();
		long prifix1Num =  Long.parseLong(parkNum.getPrifix1Num());
		String prifix2 = parkNum.getPrifix2();
		long prifix2Num = Long.parseLong(parkNum.getPrifix2Num());
		String prifix3 = parkNum.getPrifix3();
		long prifix3Num = Long.parseLong(parkNum.getPrifix3Num());
		String prifix4 = parkNum.getPrifix4();
		long prifix4Num = Long.parseLong(parkNum.getPrifix4Num());
		long unPrifixNum = Long.parseLong(parkNum.getUnPrifixNum());
		
		
		ParkModifyBizImpl.deleted();
		long parkId = 0;
		long Num1 = 0;
		long Num2 = 0;
		long Num3 = 0;
		long Num4 = 0;
		long Num5 = 0;
		for (int i = 0; i < prifix1Num; i++) {
			parkId++;
			Num1++;
			ParkPlace.setParkId(parkId);
			ParkPlace.setPrefix(prifix1);
			ParkPlace.setParkNum(String.format("%04d", Num1));
			ParkModifyBizImpl.updateById(ParkPlace);
		}
		for (int i = 0; i < prifix2Num; i++) {
			parkId++;
			Num2++;
			ParkPlace.setParkId(parkId);
			ParkPlace.setPrefix(prifix2);
			ParkPlace.setParkNum(String.format("%04d", Num2));
			ParkModifyBizImpl.updateById(ParkPlace);
		}
		for (int i = 0; i < prifix3Num; i++) {
			parkId++;
			Num3++;
			ParkPlace.setParkId(parkId);
			ParkPlace.setPrefix(prifix3);
			ParkPlace.setParkNum(String.format("%04d", Num3));
			ParkModifyBizImpl.updateById(ParkPlace);
		}
		for (int i = 0; i < prifix4Num; i++) {
			parkId++;
			Num4++;
			ParkPlace.setParkId(parkId);
			ParkPlace.setPrefix(prifix4);
			ParkPlace.setParkNum(String.format("%04d", Num4));
			ParkModifyBizImpl.updateById(ParkPlace);
		}
		
		for (int i = 0; i < unPrifixNum; i++) {
			parkId++;
			Num5++;
			ParkPlace.setParkId(parkId);
			ParkPlace.setParkNum(String.format("%04d", Num5));
			ParkModifyBizImpl.updateUnPrefixById(ParkPlace);
		}

		return "manage/modifyPark";
	}

	/*
	 * @RequestMapping("/carparkxy.action") public ModelAndView
	 * carParkXY(HttpServletRequest request, Double x, Double y) { ModelAndView
	 * modelAndView = new ModelAndView(); int x1 = (int) (x / 100); int y1 =
	 * (int) (y / 50); System.out.println(x+"z"+y); viewCarPark.setParkX(x1);
	 * viewCarPark.setParkY(y1); ViewCarPark viewCarPark1 =
	 * CarParkBizImpl.findCarParkMsg(viewCarPark);
	 * 
	 * 
	 * if(viewCarPark1==null){ list = CarParkBizImpl.fingAllCarPark();
	 * request.setAttribute("list", list); request.setAttribute("msg",
	 * "所选车位不正确！"); modelAndView.setViewName("user/userIndex");
	 * 
	 * 
	 * }else { if(viewCarPark1.getCarId()==0){
	 * request.setAttribute("viewCarPark", viewCarPark1);
	 * modelAndView.setViewName("user/addPlace"); }else { list =
	 * CarParkBizImpl.fingAllCarPark(); request.setAttribute("list", list);
	 * request.setAttribute("msg", "该车位已有车占用！");
	 * modelAndView.setViewName("user/userIndex");
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return modelAndView;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value="/carparkNum.action",method=RequestMethod.POST,
	 * produces="application/json;charset=utf-8") public @ResponseBody User
	 * carParkNum(HttpServletRequest request,String carNum) { ModelAndView
	 * modelAndView = new ModelAndView();
	 * 
	 * System.out.println(carNum+"carNum"); request.setAttribute("viewCarPark",
	 * viewCarPark1); modelAndView.setViewName("user/addPlace"); return
	 * modelAndView;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value="/carparkCheck.action", method=RequestMethod.POST)
	 * public ModelAndView carParkCheck(HttpServletRequest request, String
	 * carNum, int parkId,MultipartFile fileact) { ModelAndView modelAndView =
	 * new ModelAndView(); //
	 * 用车号查车id，用车id去查停车位信息，如果停车位为空则可以停，如果不为空则提示，该车已在车库中停放，请重新输入。
	 * tbCar.setCarNum(carNum); TbCar tbCar2 = CarParkBizImpl.findCar(tbCar);
	 * long carId = tbCar2.getCarId(); // 用车id去查停车位信息,判断该车的状态。
	 * 
	 * 
	 * tbParkPlace.setCarId(carId); TbParkPlace tbParkPlace2 =
	 * CarParkBizImpl.findCarPlace(tbParkPlace);
	 * 
	 * if (tbParkPlace2 == null) { // 查出车位为空，可插入数据。 // 1、车位将carid插入
	 * 
	 * tbParkPlace.setParkId(parkId);
	 * CarParkBizImpl.updateParkPlace(tbParkPlace);
	 * 
	 * // 2、上传图片，
	 * 
	 * 
	 * 
	 * //设置时间 Date day = new Date(); String str = (new
	 * SimpleDateFormat("yyyyMMddHHmmssSSS")).format(day); //上传图片 String
	 * filename = fileact.getOriginalFilename(); String path = "E:\\upload\\" +
	 * carNum; File destFile1 = new File(path); if (!destFile1.exists()) {
	 * destFile1.mkdirs(); } File destFile = new File(path, filename);
	 * 
	 * try { fileact.transferTo(destFile); } catch (IllegalStateException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * //将src插入数据库中。 tbCar2.setParkImgSrc(destFile.getAbsolutePath());
	 * 
	 * CarParkBizImpl.updateCarParkSrc (tbCar2);
	 * 
	 * //下发数据。 list = CarParkBizImpl.fingAllCarPark();
	 * 
	 * request.setAttribute("list", list);
	 * modelAndView.setViewName("user/userIndex");
	 * 
	 * } else { // 查出车位不为空，可插入数据。即该车已在车位上了。 viewCarPark.setParkId(parkId);
	 * ViewCarPark viewCarPark1 = CarParkBizImpl.findCarParkMsg1(viewCarPark);
	 * System.out.println(viewCarPark1.getParkX()+" fdgd ");
	 * modelAndView.setViewName("user/addPlace"); request.setAttribute("msg",
	 * "该车已在停车位上了，请重新输入车牌！"); request.setAttribute("viewCarPark", viewCarPark1);
	 * 
	 * }
	 * 
	 * 
	 * return modelAndView; }
	 */

}
