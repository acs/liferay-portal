/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Michael Young
 */
public class DefaultLandingPageAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String path = PrefsPropsUtil.getString(
			companyId, PropsKeys.DEFAULT_LANDING_PAGE_PATH);

		if (_log.isInfoEnabled()) {
			_log.info(
				PropsKeys.DEFAULT_LANDING_PAGE_PATH + StringPool.EQUAL + path);
		}

		if (Validator.isNotNull(path)) {
			LastPath lastPath = new LastPath(StringPool.BLANK, path);

			HttpSession session = request.getSession();

			session.setAttribute(WebKeys.LAST_PATH, lastPath);
		}

		// The commented code shows how you can programmaticaly set the user's
		// landing page. You can modify this class to utilize a custom algorithm
		// for forwarding a user to his landing page. See the references to this
		// class in portal.properties.

		/*Map<String, String[]> params = new HashMap<String, String[]>();

		params.put("p_l_id", new String[] {"1806"});

		LastPath lastPath = new LastPath("/c", "/portal/layout", params);

		session.setAttribute(WebKeys.LAST_PATH, lastPath);*/
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultLandingPageAction.class);

}