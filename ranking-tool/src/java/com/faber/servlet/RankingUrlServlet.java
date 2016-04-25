package com.faber.servlet;

import com.faber.bussiness.RankingUrl;
import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Hung Hau
 */
@WebServlet(name = "RankingUrlServlet", urlPatterns = {"/getRankingUrl"})
public class RankingUrlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RankingUrl ranking = new RankingUrl();
        String json = request.getParameter("json");
        JSONObject jsonObject;
        PrintWriter out = response.getWriter();

        try {
            jsonObject = new JSONObject(json);
            String keyword = URLDecoder.decode(jsonObject.getString("keyword"));
            String url =  jsonObject.getString("url");
            String rank = ranking.getRanking(keyword, url);
            out.print(rank);
            
        } catch (JSONException ex) {
            Logger.getLogger(RankingUrlServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
