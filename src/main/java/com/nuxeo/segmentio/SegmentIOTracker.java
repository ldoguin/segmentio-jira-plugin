package com.nuxeo.segmentio;

import java.util.Map;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.JiraVelocityUtils;
import com.atlassian.jira.util.collect.MapBuilder;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;
import com.nuxeo.segmentio.config.SegmentIOConfig;

public class SegmentIOTracker implements ContextProvider {

	private final JiraAuthenticationContext authenticationContext;

	private final SegmentIOConfig segmentIOConfig;

	private Map<String, String> params;

	public SegmentIOTracker(JiraAuthenticationContext authenticationContext,
			SegmentIOConfig segmentIOConfig) {
		this.authenticationContext = authenticationContext;
		this.segmentIOConfig = segmentIOConfig;
	}

	@Override
	public void init(Map<String, String> params) throws PluginParseException {
		this.params = params;
	}

	@Override
	public Map<String, Object> getContextMap(Map<String, Object> context) {
		final MapBuilder<String, Object> paramsBuilder = MapBuilder
				.newBuilder(JiraVelocityUtils.getDefaultVelocityParams(context,
						authenticationContext));
		paramsBuilder.addAll(params);
		Boolean trackLogin = segmentIOConfig.getTrackLogin();
		if (trackLogin) {
			ApplicationUser user = authenticationContext.getUser();
			if (user != null) {
				paramsBuilder.add("username", user.getUsername());
				paramsBuilder.add("name", user.getDisplayName());
				paramsBuilder.add("email", user.getEmailAddress());
			}
		}
		ApplicationProperties applicationProperties = ComponentAccessor
				.getApplicationProperties();
		String baseUrl = applicationProperties.getString(APKeys.JIRA_BASEURL);
		paramsBuilder.add("baseUrl", baseUrl);
		paramsBuilder.add("segmentIOKey", segmentIOConfig.getSegmentIOKey());
		return paramsBuilder.toMap();
	}

}
