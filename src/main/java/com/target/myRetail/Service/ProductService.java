package com.target.myRetail.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.target.myRetail.model.Product;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class ProductService {

    public Product getProduct(String id) throws IOException {
        Product myProduct = new Product();

        URL obj = null;
        try {
            String redSkyURI = String.format("https://redsky.target.com/v2/pdp/tcin/%s?excludes=promotion,esp,bulk_ship,deep_red_labels,question_answer_statistics,rating_and_review_statistics,rating_and_review_reviews,available_to_promise_network,promotion", id);
            //System.out.println(redSkyURI);
            //System.out.println("\n-------REQUEST URL --------");
            //System.out.println("\nSending 'GET' request to URL : " + redSkyURI);
            //System.out.println("\n-----------------------------");
            obj = new URL(redSkyURI);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        System.out.println("\n-------RESPONSE Code --------");
        int responseCode = con.getResponseCode();
        //System.out.println("\nResponse Code : " + responseCode);
        //System.out.println("\n-----------------------------");
        if (responseCode == 200 || responseCode == 201) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            // Parse json response string
            JsonObject jsonResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
            String strPrice = jsonResponse.getAsJsonObject("product").getAsJsonObject("price").getAsJsonObject("listPrice").get("price").toString();
            Double price = Double.parseDouble(strPrice);
            String title = jsonResponse.getAsJsonObject("product").getAsJsonObject("item").getAsJsonObject("product_description").get("title").toString();

            myProduct.setId(id);
            myProduct.setPrice(price);
            myProduct.setName(title);
        }
        return myProduct;
    }
}