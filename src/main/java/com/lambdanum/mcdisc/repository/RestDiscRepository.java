package com.lambdanum.mcdisc.repository;

import com.google.gson.GsonBuilder;
import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McDiscMod;
import com.lambdanum.mcdisc.infrastructure.util.HttpUtil;
import com.lambdanum.mcdisc.model.Disc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestDiscRepository extends DiscRepository {

    private Logger logger = LogManager.getLogger(McDiscMod.MODID);

    private String serverUrl;
    private HttpUtil httpUtil = new HttpUtil(new GsonBuilder().serializeNulls().create());

    public RestDiscRepository(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public List<Disc> getDiscs() {
        try {
            return Arrays.asList(httpUtil.get(serverUrl, Disc[].class));
        } catch (RuntimeException e) {
            logger.error("Could not fetch disc list from URL " + serverUrl);
            return Collections.emptyList();
        }
    }
}
