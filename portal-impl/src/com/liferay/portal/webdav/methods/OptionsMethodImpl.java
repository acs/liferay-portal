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

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.webdav.WebDAVRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class OptionsMethodImpl implements Method {

	public int process(WebDAVRequest webDavRequest) {
		HttpServletResponse response = webDavRequest.getHttpServletResponse();

		if (webDavRequest.getWebDAVStorage().isSupportsClassTwo()) {
			response.addHeader("DAV", "1,2");
		}
		else {
			response.addHeader("DAV", "1");
		}

		response.addHeader("Allow", Method.SUPPORTED_METHODS);
		response.addHeader("MS-Author-Via", "DAV");

		return HttpServletResponse.SC_OK;
	}

}