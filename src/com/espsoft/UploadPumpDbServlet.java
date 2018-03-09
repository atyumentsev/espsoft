package com.espsoft;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "UploadPumpDbServlet")
public class UploadPumpDbServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 20;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        ArrayList<String> result;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        try {
            // Parse the request
            List items = upload.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);

                    result = PumpDBLoader.load(filePath);

                    for (String res : result) {
                        out.print(res + "<br>");
                    }
                }
            }

            // displays done.jsp page after upload finished
//            getServletContext().getRequestDispatcher("/done.jsp").forward(
//                    request, response);



        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
