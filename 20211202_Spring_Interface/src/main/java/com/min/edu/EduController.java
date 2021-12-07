package com.min.edu;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.IService;
import com.min.edu.vo.EduVo;

@Controller
public class EduController {
	
	@Autowired
	private IService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/selectBoard.do", method=RequestMethod.GET)
	public String selectBoard(Model model) {
		logger.info("welcome EduCOntroller selectBoard {}", new Date());
		List<EduVo> lists  = service.selectBoard();
		model.addAttribute("lists",lists);
		return "boardList";
	}
	
	/*
	 * parameter를 받는 방법
	 *   - view의 form요소의 name과 같은 명을 찾아서 객체로 만든다.
	 *   - vo/dto 객체를 통해서 set의 메소드를 호출하며 vo/dto로 만든다.
	 *   - map 객체를 통해서 Key/Value로 만든다.
	 *   
	 *   - 하지만 view에서 name이 없는 명을 사용한다면 null이다.
	 */
	@RequestMapping(value="/insertBoard", method=RequestMethod.POST)
	public String insertBoard(EduVo vo) {//EduVo에 Setter가 있어야함 ex)setFirstName(..) : firstName
		logger.info("Welcome VO로 Parameter 전송을 처리함 {}", vo);
		int cnt = service.insertBoard(vo);
		if(cnt>0) { //insert delete update는 성공한 row 갯수를 반환
			return "redirect:/selectBoard.do";
		}else {
			return "error";
		}
	}
	
	@RequestMapping(value="/insertBoardMap.do", method=RequestMethod.POST)
	public String insertBoardMap(@RequestParam Map<String, String> paramMap) { //view에서 요청한 form요소는 Map의 형태로 받기 위해서는 Bind Annotation이 반드시 필요하다
		logger.info("Welcome Map으로 Parameter를 받음 {}", paramMap);
		return "redirect:/selectBoard.do";
	}
	/*
	 * @RequestParam(value="page", defaultValue = "페이지") String page
	 * 입력 받은 값이 없다면(화면에서 name은 있으나 값이 없는 경우 "") defaultValue의 값을 입력해 준다.
	 * 
	 * @RequestParam(value="content", required= faluse) String content
	 * 화면에서 name=content가 존재하지 않으면 메소드가 동작을 하지 않습니다 따라서 전체적인 오류가 발생
	 * content의 값이 필수가 아닌 선택이라면 입력시 있거나 혹은 없을수 있습니다. 이럴때 requried=false를 통해서 있다면 값을 받고 아니면 오류가 발생하지 않고 진행
	 */
	@RequestMapping(value="/insertRequestParam.do", method = RequestMethod.POST)
	public String insertRequsetParm(
			@RequestParam("a") String id,
			@RequestParam("b") String title,
			@RequestParam("c") String content,
			@RequestParam(value="page", defaultValue = "페이지") String page,
			@RequestParam(value="content", required = false) String cotent2
			) {
		System.out.printf("a:%s, b:%s, c:%s, page:%s %n", id, title, content, page);
//		return "redirect:/selectBoard.do";
		return null;
	}
	
	@RequestMapping(value="/com/min/{edu}/PramPath.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pathVariable(@PathVariable("edu")String path, String val, Model model) {
		model.addAttribute("val", val);
		return path+"/path";
	}
	
	@RequestMapping(value="/transactionTest.do", method = RequestMethod.POST)
	public String transactionTest(EduVo vo) {
		logger.info("Welcome transactionTest 전달받은 VO{}", vo);
		int totalCnt = service.TransactionTest(vo);
		logger.info("Welcome transaction의 처리 결과 {}", totalCnt);
		logger.info("Welcome transactionTest 결과 값 VO{}", vo);
		return null;
		
	}
}
