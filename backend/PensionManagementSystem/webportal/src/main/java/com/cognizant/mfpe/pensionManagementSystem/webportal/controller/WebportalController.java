package com.cognizant.mfpe.pensionManagementSystem.webportal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.mfpe.pensionManagementSystem.webportal.dao.WebportalServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.Pensioner;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.ProcessPensionInput;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.UserData;
import com.cognizant.mfpe.pensionManagementSystem.webportal.restClients.PensionerDetailClient;
import com.cognizant.mfpe.pensionManagementSystem.webportal.restClients.ProcessPensionClient;

@Controller
public class WebportalController {
	@Autowired
	UserData admin;
	private static Logger logger = LoggerFactory.getLogger(WebportalController.class);
	@Autowired
	ProcessPensionClient processPensionClient;
	@Autowired
	PensionerDetailClient pensionerDetailClient;
	@Autowired
	WebportalServiceDao webportalServiceDao;
	private static final String TOKEN = "token";
	private static final String AADHAAR = "aadhaar";
	private static final String SERVICE_CHARGE = "serviceCharge";
	private static final String PENSION_AMOUNT = "pensionAmount";

	double pensionAmount = 0.0d;

	/**
	 * The method at this end point retrieves pension detail
	 * 
	 * @param HttpSession, Pensioner, ModelMap
	 * @return ModelAndView
	 */
	@GetMapping("/getPensionDetail")
	public ModelAndView getPensionDetail(HttpSession session, @ModelAttribute("pensioner") Pensioner pensioner,
			ModelMap model) {
		logger.info("START");
		ModelAndView mav;
		if (webportalServiceDao.isSessionValid((String) session.getAttribute(TOKEN))) {

			try {

				Pensioner updatedPensioner = processPensionClient.getPensionDetail((String) session.getAttribute(TOKEN),
						pensioner.getName(), new SimpleDateFormat("dd-MM-yyyy").format(pensioner.getDateOfBirth()),
						pensioner.getPanNumber(), pensioner.getAadhaarNumber(), pensioner.getTypeOfPension());
				if (updatedPensioner == null) {
					model.put("calculatePensionFailureMessage",
							"Invalid pensioner detail provided, please provide valid detail");
					mav = new ModelAndView("calculatePensionFail");
					logger.info("END");

					return mav;
				}
				model.put("pensioner", updatedPensioner);
				session.setAttribute(PENSION_AMOUNT, updatedPensioner.getPensionAmount());
				session.setAttribute(SERVICE_CHARGE, updatedPensioner.getBankServiceCharge());
				session.setAttribute(AADHAAR, updatedPensioner.getAadhaarNumber());

				mav = new ModelAndView("calculatePensionSuccess");
			} catch (

			Exception e) {

				mav = new ModelAndView("exception");
				logger.info("EXCEPTION");

				return mav;

			}

			logger.info("END");

			return mav;
		}
		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * The method at this end point inititates pension disbursement
	 * 
	 * @param HttpSession, ProcessPensionInput, ModelMap
	 * @return ModelAndView
	 */
	@PostMapping(value = "/postProcessPension")
	public ModelAndView getProcessingCode(HttpSession session,
			@ModelAttribute("processPensionInput") ProcessPensionInput processPensionInput, ModelMap model) {
		logger.info("START");
		int processCode;
		ModelAndView mav;
		if (webportalServiceDao.isSessionValid((String) session.getAttribute(TOKEN))) {

			try {
				processCode = processPensionClient.getProcessingCode((String) session.getAttribute(TOKEN),
						processPensionInput);
				if (processCode == 10) {
					Pensioner banklogPensioner = pensionerDetailClient.getPensionerDetailByAadhaar(
							(String) session.getAttribute(TOKEN), processPensionInput.getAadhaarNumber());
					model.put("banklog", banklogPensioner);
					model.put("dateOfTransaction", new Date());
					model.put("netAmount", ((Math.round(
							(banklogPensioner.getPensionAmount() - banklogPensioner.getBankServiceCharge()) * 100.0))
							/ 100.0));
					model.put("processPensionSuccessMessage", "Pension Disbursement Success");
					mav = new ModelAndView("processPensionSuccess");
					logger.info("END");

					return mav;
				}
			} catch (Exception e) {
				logger.info("EXCEPTION");

				mav = new ModelAndView("exception");
				return mav;
			}

			model.put("processPensionFailureMessage",
					"Pension amount calculated is wrong, Please redo the calculation");
			mav = new ModelAndView("processPensionFail");
			logger.info("END");

			return mav;
		}
		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * The method at this end point returns login page
	 * 
	 * @param HttpSession, Pensioner
	 * @return ModelAndView
	 */
	@GetMapping("/")
	public ModelAndView getLogin(HttpSession session, @ModelAttribute("pensioner") Pensioner pensioner) {
		logger.info("START");

		if (session != null && (String) session.getAttribute(TOKEN) != null
				&& webportalServiceDao.isSessionValid((String) session.getAttribute(TOKEN))) {
			logger.info("END");

			return new ModelAndView("pensionerDetailForm");
		}
		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * The method at this end point verifies login
	 * 
	 * @param HttpSession, Pensioner, UserData, ModelMap
	 * @return ModelAndView
	 */
	@PostMapping("/login")
	public ModelAndView postLogin(HttpSession session, @ModelAttribute("pensioner") Pensioner pensioner,
			@ModelAttribute UserData user, ModelMap warning) {
		logger.info("START");
		logger.info("END");

		return new ModelAndView(webportalServiceDao.postLogin(user, session, warning));

	}

	/**
	 * The method at this end point returns pensioner detail form
	 * 
	 * @param HttpSession, Pensioner
	 * @return ModelAndView
	 */
	@GetMapping("/pensionerDetailForm")
	public ModelAndView getPensionerDetailForm(HttpSession session, @ModelAttribute("pensioner") Pensioner pensioner) {
		logger.info("START");

		if (webportalServiceDao.isSessionValid((String) session.getAttribute(TOKEN))) {
			logger.info("END");
			return new ModelAndView("pensionerDetailForm");
		}
		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * The method at this end point invalidates the session
	 * 
	 * @param HttpSession
	 * @return ModelAndView
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		logger.info("START");
		session.invalidate();

		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * The method at this end point returns process pension form
	 * 
	 * @param HttpSession, ProcessPensionInput, ModelMap
	 * @return ModelAndView
	 */
	@GetMapping("/processPensionForm")
	public ModelAndView getProcessPensionForm(HttpSession session,
			@ModelAttribute("processPensionInput") ProcessPensionInput processPensionInput, ModelMap model) {
		logger.info("START");

		if (webportalServiceDao.isSessionValid((String) session.getAttribute(TOKEN))) {
			logger.info("END");
			model.put(PENSION_AMOUNT, session.getAttribute(PENSION_AMOUNT));
			model.put(SERVICE_CHARGE, session.getAttribute(SERVICE_CHARGE));
			model.put(AADHAAR, session.getAttribute(AADHAAR));

			return new ModelAndView("processPensionForm");
		}
		logger.info("END");

		return new ModelAndView("login");
	}

	/**
	 * This method binds the jsp date input to java.util.Date.
	 * 
	 * @param WebBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("START");

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		logger.info("END");

	}
}
