package com.example.smtrick.smartnanded.Views.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.example.smtrick.smartnanded.utilities.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.smtrick.smartnanded.constants.Constant.GLOBAL_DATE_FORMATE;


public class View_User_Report_Activity extends AppCompatActivity implements View.OnClickListener {


    private Button AddmainData;

    private TextView txtmemberward, txtmembername, txtmemberbirthdate, txtmembereducation, txtmemberoccupation;
    private String Smemberward, Smembername, Smemberbirthdate, Smembereducation, Smemberoccupation;
    private TextView wardnumber, name, age, dob, education, heading, details;

    User invoice;
    LeedRepository leedRepository;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private File pdfFile;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_report_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        getSupportActionBar().setHomeButtonEnabled(true);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        invoice = (User) intent.getSerializableExtra("report");
        leedRepository = new LeedRepositoryImpl();

        // AddsubData = (Button)findViewById(R.id.submit);
        AddmainData = (Button) findViewById(R.id.submit);
        //spinneroccu = (Spinner)findViewById(R.id.occupation);
        txtmemberward = (TextView) findViewById(R.id.wardnumber);
        txtmembername = (TextView) findViewById(R.id.membername);
        txtmemberbirthdate = (TextView) findViewById(R.id.dateofbirth);
        txtmembereducation = (TextView) findViewById(R.id.education);


        heading = (TextView) findViewById(R.id.reportheading);
        details = (TextView) findViewById(R.id.reportdetails);
        wardnumber = (TextView) findViewById(R.id.doctor);
        name = (TextView) findViewById(R.id.patient);
        age = (TextView) findViewById(R.id.age);
        dob = (TextView) findViewById(R.id.memberdob);
        education = (TextView) findViewById(R.id.membereducation);

        getdata();
        AddmainData.setOnClickListener(this);
    }


    private void getdata() {

        Smemberward = invoice.getUserName();
        Smembername = invoice.getMobileNumber();
        Smemberbirthdate = invoice.getAgentId();
        Smembereducation = invoice.getRole();
        Smemberoccupation = Utility.convertMilliSecondsToFormatedDate(invoice.getCreatedDateTimeLong(), GLOBAL_DATE_FORMATE);


        if (Smemberward != null) {
            txtmemberward.setText(Smemberward);
        }
        if (Smembername != null) {
            txtmembername.setText(Smembername);
        }
        if (Smemberbirthdate != null) {
            txtmemberbirthdate.setText(Smemberbirthdate);
        }
        if (Smembereducation != null) {
            txtmembereducation.setText(Smembereducation);
        }
        if (Smemberoccupation != null) {
            txtmemberoccupation.setText(Smemberoccupation);
        }



    }


    @Override
    public void onClick(View v) {
        if (v == AddmainData) {
            try {
                createPdfWrapper();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPdfWrapper() throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(View_User_Report_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(View_User_Report_Activity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException {

        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MEMBERS DATABASE/MEMBER REPORTS/";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            Log.d("PDFCreator", "PDF Path: " + path);

            File file = new File(dir, invoice.getUserName() + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            doc.open();
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);

            Paragraph address = new Paragraph("details");
            Paragraph Date = new Paragraph("Date: "+formattedDate);
            /* You can also SET FONT and SIZE like this */
            Font paraFont2 = new Font(Font.FontFamily.HELVETICA);
            paraFont2.setSize(11);
            address.setAlignment(Paragraph.ALIGN_CENTER);
            address.setFont(paraFont2);
            doc.add(address);

            Paragraph blankspace = new Paragraph("\n");
            doc.add(blankspace);


            Font paraFonto = new Font(Font.FontFamily.HELVETICA);
            paraFonto.setSize(11);
            Date.setAlignment(Paragraph.ALIGN_RIGHT);
            Date.setFont(paraFonto);
            doc.add(Date);

            Paragraph blankspace0 = new Paragraph("\n");
            doc.add(blankspace0);
            doc.add(blankspace0);
            Phrase phrase5 = new Phrase();
            PdfPCell phraseCell5 = new PdfPCell();
            phraseCell5.addElement(phrase5);
            PdfPTable phraseTable5 = new PdfPTable(2);
            phraseTable5.setWidthPercentage(100);
            phraseTable5.setWidths(new int[]{50, 50});
            phraseTable5.setHorizontalAlignment(Element.ALIGN_CENTER);

            phraseTable5.addCell("NAME");
            phraseTable5.addCell(invoice.getUserName());

            phrase5.setFont(paraFont2);

            Phrase phraseTableWrapper5 = new Phrase();
            phraseTableWrapper5.add(phraseTable5);
            doc.add(phraseTableWrapper5);
/////////////////////////////////////////////////////////////////////////
            Phrase phrase6 = new Phrase();
            PdfPCell phraseCell6 = new PdfPCell();
            phraseCell6.addElement(phrase6);
            PdfPTable phraseTable6 = new PdfPTable(2);
            phraseTable6.setWidthPercentage(100);
            phraseTable6.setWidths(new int[]{50, 50});
            phraseTable6.setHorizontalAlignment(Element.ALIGN_CENTER);

            phraseTable6.addCell("MOBILE");
            phraseTable6.addCell(invoice.getMobileNumber());

            phrase6.setFont(paraFont2);

            Phrase phraseTableWrapper6 = new Phrase();
            phraseTableWrapper6.add(phraseTable6);
            doc.add(phraseTableWrapper6);
/////////////////////////////////////////////////////////////////////////
            Phrase phrase7 = new Phrase();
            PdfPCell phraseCell7 = new PdfPCell();
            phraseCell7.addElement(phrase7);
            PdfPTable phraseTable7 = new PdfPTable(2);
            phraseTable7.setWidthPercentage(100);
            phraseTable7.setWidths(new int[]{50, 50});
            phraseTable7.setHorizontalAlignment(Element.ALIGN_CENTER);

            phraseTable7.addCell("ROLE");
            phraseTable7.addCell(invoice.getRole());

            phrase7.setFont(paraFont2);

            Phrase phraseTableWrapper7 = new Phrase();
            phraseTableWrapper7.add(phraseTable7);
            doc.add(phraseTableWrapper7);
/////////////////////////////////////////////////////////////////////////
            Phrase phrase8 = new Phrase();
            PdfPCell phraseCell8 = new PdfPCell();
            phraseCell8.addElement(phrase8);
            PdfPTable phraseTable8 = new PdfPTable(2);
            phraseTable8.setWidthPercentage(100);
            phraseTable8.setWidths(new int[]{50, 50});
            phraseTable8.setHorizontalAlignment(Element.ALIGN_CENTER);

            phraseTable8.addCell("ID");
            phraseTable8.addCell(invoice.getAgentId());

            phrase8.setFont(paraFont2);

            Phrase phraseTableWrapper8 = new Phrase();
            phraseTableWrapper8.add(phraseTable8);
            doc.add(phraseTableWrapper8);
/////////////////////////////////////////////////////////////////////////
            Phrase phrase9 = new Phrase();
            PdfPCell phraseCell9 = new PdfPCell();
            phraseCell9.addElement(phrase9);
            PdfPTable phraseTable9 = new PdfPTable(2);
            phraseTable9.setWidthPercentage(100);
            phraseTable9.setWidths(new int[]{50, 50});
            phraseTable9.setHorizontalAlignment(Element.ALIGN_CENTER);

            String date = Utility.convertMilliSecondsToFormatedDate(invoice.getCreatedDateTimeLong(), GLOBAL_DATE_FORMATE);
            phraseTable9.addCell("REGISTERED DATE");
            phraseTable9.addCell(date);

            phrase9.setFont(paraFont2);

            Phrase phraseTableWrapper9 = new Phrase();
            phraseTableWrapper9.add(phraseTable9);
            doc.add(phraseTableWrapper9);
/////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////
            Toast.makeText(View_User_Report_Activity.this, "PDF Generated", Toast.LENGTH_SHORT).show();

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } finally {
            doc.close();
        }

        openPdf1();
    }


    void openPdf1() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MEMBERS DATABASE/MEMBER REPORTS/";
        File file = new File(path, invoice.getUserName() + ".pdf");
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        Intent j = Intent.createChooser(intent, "Choose an application to open with:");
        startActivity(j);
    }
}