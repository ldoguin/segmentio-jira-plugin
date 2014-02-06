package com.nuxeo.segmentio.config;

import com.atlassian.jira.web.action.JiraWebActionSupport;

public class SegmentIOConfigAction extends JiraWebActionSupport {
	private SegmentIOConfig config;
	private String apiKey;
	private boolean trackLogin;
	private String[] trackLoginSelect;

	public SegmentIOConfigAction(SegmentIOConfig config) {
		this.config = config;
		this.apiKey = config.getSegmentIOKey();
		this.trackLogin = config.getTrackLogin();
	}

	@Override
	protected String doExecute() throws Exception {
		return SUCCESS;
	}

	public String doUpdate() {
		config.storeSegmentIOKey(apiKey);
		if (trackLoginSelect != null ) {
			trackLogin = true;
		} else {
			trackLogin = false;
		}
		config.storeTrackLogin(trackLogin);
		return getRedirect("SegmentIOConfigAction.jspa");
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String[] getTrackLoginSelect() {
		return trackLoginSelect;
	}

	public void setTrackLoginSelect(String[] trackLoginSelect) {
		this.trackLoginSelect = trackLoginSelect;
	}

	public boolean isTrackLogin() {
		return trackLogin;
	}

	public void setTrackLogin(boolean trackLogin) {
		this.trackLogin = trackLogin;
	}



}