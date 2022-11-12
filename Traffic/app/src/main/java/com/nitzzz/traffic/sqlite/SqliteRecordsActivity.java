package com.nitzzz.traffic.sqlite;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.other.BaseActivity;

import java.util.ArrayList;

public class SqliteRecordsActivity extends BaseActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_records);

        start();
    }

    private void start(){

        String table    = getIntent().getStringExtra("table");

        android.widget.TextView heading    = findViewById(R.id.heading);
        WebView webView     = findViewById(R.id.webview);
        heading.setText(table);
        heading.setOnClickListener(v -> createWebPrintJob(webView));

        String mimeType = "text/html";
        String encoding = "utf-8";

//        webView.loadUrl("file:///android_res/raw/invoice.html");

//        getContentHTML(table);

        // Load html source code into webview to show the html content.
        webView.loadDataWithBaseURL(null, getContentHTML(table), mimeType, encoding, null);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

    }

    private String getContentHTML(String tableName){

        String html =   "<html> \n" +
                "<head> \n"+
                "<style>\n" +
                "table {\n" +
                "  border: 2px solid blue; !important;" +
                "  border-collapse: collapse;" +
                "  font-family: arial, sans-serif;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "  td, th {\n" +
                "  border: 2px solid blue; !important;" +
                "  border-collapse: collapse;" +
                "  font-size: 30px !important;" +
                "  text-align: left;\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even){background-color: #f7e172}" +
                "\n" +
                "</style>"+
                "</head> \n"+
                "<body>"+
                "<table style='overflow-x:auto; '>\n";

        //get tables name
        SqliteManager database = new SqliteManager(this);
        ArrayList<String[]> data = new ArrayList<>();
        data.addAll(database.getTableContents("select * from " + tableName));

        for(int row=0; row<data.size(); row++){

            //for head
            if(row == 0){

                html += "<tr>\n";
                for(int col=0; col<data.get(row).length; col++){
                    html += "<th>"+data.get(row)[col]+"</th>\n" ;
                }
                html += "</tr>";

            }else {

                //for body
                html += "<tr>\n";
                for (int col = 0; col < data.get(row).length; col++) {

                    html += "<td>" + data.get(row)[col] + "</td>\n";
                }
                html += "</tr>";
            }
        }

        html += "</table>\n" +
                "</body>\n" +
                "</html>";
        return  html;
    }

    private String getDummyData(){

        String html =   "<html> \n" +
                "<head> \n"+
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>"+
                "</head> \n"+
                "<body>"+
                "<table>\n";

        /*column*/
        html += "<tr>\n";
        html += "<th>Name</th>\n" ;
        html += "<th>Mobile</th>\n" ;
        html += "<th>Address</th>\n" ;
        html += "<th>City</th>\n" ;
        html += "</tr>";

        //for body
        html += "<tr>\n";
        html += "<td>Nitesh </td>\n";
        html += "<td>123456 </td>\n";
        html += "<td>dewas </td>\n";
        html += "<td>dewas </td>\n";
        html += "<tr>\n";

        html += "<tr>\n";
        html += "<td>Nitesh </td>\n";
        html += "<td>123456 </td>\n";
        html += "<td>dewas </td>\n";
        html += "<td>dewas </td>\n";
        html += "<tr>\n";

        html += "<tr>\n";
        html += "<td>Nitesh </td>\n";
        html += "<td>123456 </td>\n";
        html += "<td>dewas </td>\n";
        html += "<td>dewas </td>\n";
        html += "<tr>\n";

        html += "<tr>\n";
        html += "<td>Nitesh </td>\n";
        html += "<td>123456 </td>\n";
        html += "<td>dewas </td>\n";
        html += "<td>dewas </td>\n";
        html += "<tr>\n";


        html += "</table>\n" +
                "</body>\n" +
                "</html>";
        return  html;

    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) getBaseContext().getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

    }

}