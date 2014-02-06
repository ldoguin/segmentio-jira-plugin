package com.nuxeo.segmentio.config;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class SegmentIOConfig {

	final PluginSettingsFactory pluginSettingsFactory;

	String SEGMENT_IO_CONFIG_KEY = "com.nuxeo.segmentio.config.apikey";

	String SEGMENT_IO_CONFIG_TRACK_LOGIN = "com.nuxeo.segmentio.config.trackLogin";

	public SegmentIOConfig(PluginSettingsFactory pluginSettingsFactory) {
		this.pluginSettingsFactory = pluginSettingsFactory;
	}

	public void storeSegmentIOKey(String value) {
		pluginSettingsFactory.createGlobalSettings().put(SEGMENT_IO_CONFIG_KEY,
				value);
	}

	public String getSegmentIOKey() {
		Object apiKey = pluginSettingsFactory.createGlobalSettings().get(
				SEGMENT_IO_CONFIG_KEY);
		if (apiKey != null && apiKey instanceof String) {
			return (String) apiKey;
		} else {
			return null;
		}
	}

	public void storeTrackLogin(boolean trackLogin) {
		if (trackLogin) {
			pluginSettingsFactory.createGlobalSettings().put(
					SEGMENT_IO_CONFIG_TRACK_LOGIN, "true");
		} else {
			pluginSettingsFactory.createGlobalSettings().put(
					SEGMENT_IO_CONFIG_TRACK_LOGIN, "false");
		}
	}

	public Boolean getTrackLogin() {
		return Boolean.valueOf((String) pluginSettingsFactory
				.createGlobalSettings().get(SEGMENT_IO_CONFIG_TRACK_LOGIN));
	}
}