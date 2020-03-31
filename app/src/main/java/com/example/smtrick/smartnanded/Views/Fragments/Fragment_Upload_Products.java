package com.example.smtrick.smartnanded.Views.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.ImagePickerActivity;
import com.example.smtrick.smartnanded.Views.Activities.Main_Activity;
import com.example.smtrick.smartnanded.Views.Adapters.Product_SubImages_Adapter;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.interfaces.OnFragmentInteractionListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment_Upload_Products extends Fragment implements View.OnClickListener {

    private static final int REQUEST_PICK_IMAGE = 1002;
    private static final int RESULT_LOAD_IMAGE = 1;
    String image;

    //view objects
    private Button buttonChoose, buttonChooseSubImages;
    private Button buttonUpload;
    private EditText Idescription, editTextName, editTextPrice, editTextCarModel, editTextCarYear, editTextCarKMDriven,
            editTextCarFuel, editTextbikeModel, editTextbikeYear, editTextbikeKMDriven,
            editTextsalPeriod, editTextPositionType, editTextSalFrom, editTextSalTo, editTextProType, editTextProBedrooms,
            editTextproFurnishing, editTextproConstructionStatus, editTextproListedBy, editTextproCarpetArea,
            editTextproProjectName, editTextproParkings;
    ;
    private ImageView imageView;
    private Spinner spinnerCategory;
    private RecyclerView recycleSubImages;
    private Product_SubImages_Adapter productSubImagesAdapter;
    private LinearLayout layoutCarModel, layoutCarYear, layoutCarFuel, layoutCarKM, layoutbikeModel, layoutbikeYear,
            layoutbikeKM, layoutSalPeriod, layoutPositionType, layoutSalFrom,
            layoutSalTo, layoutProType, layoutProBedrooms, layoutproFurnishing, layoutproConstructionStatus, layoutproListedBy,
            layoutproCarpetArea, layoutproProjectName, layoutproParkings;


    //uri to store file
    private Uri filePath;
    private String downloadurl1;
    private List<Uri> fileDoneList;
    private ArrayList<String> fileDoneList1;

    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;

    // NOTE: Removed Some unwanted Boiler Plate Codes
    private OnFragmentInteractionListener mListener;

    public Fragment_Upload_Products() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_imageupload, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        fileDoneList = new ArrayList<>();
        fileDoneList1 = new ArrayList<>();

        buttonChoose = (Button) view.findViewById(R.id.buttonChoose);
        buttonChooseSubImages = (Button) view.findViewById(R.id.buttonChooseSubImages);
        buttonUpload = (Button) view.findViewById(R.id.buttonUpload);

        imageView = (ImageView) view.findViewById(R.id.imageView);

        editTextName = (EditText) view.findViewById(R.id.editText);
        editTextPrice = (EditText) view.findViewById(R.id.editTextProductPrice);
        Idescription = (EditText) view.findViewById(R.id.description);
        editTextCarModel = (EditText) view.findViewById(R.id.editTextCarModel);
        editTextCarYear = (EditText) view.findViewById(R.id.editTextCarYear);
        editTextCarFuel = (EditText) view.findViewById(R.id.editTextCarFuel);
        editTextCarKMDriven = (EditText) view.findViewById(R.id.editTextCarKMDriven);

         editTextbikeModel = (EditText) view.findViewById(R.id.editTextbikeModel);
        editTextbikeYear = (EditText) view.findViewById(R.id.editTextbikeYear);
        editTextbikeKMDriven = (EditText) view.findViewById(R.id.editTextbikeKMDriven);

        editTextsalPeriod = (EditText) view.findViewById(R.id.editTextsalaryperiod);
        editTextPositionType = (EditText) view.findViewById(R.id.editTextPositionType);
        editTextSalFrom = (EditText) view.findViewById(R.id.editTextSalaryFrom);
        editTextSalTo = (EditText) view.findViewById(R.id.editTextSalaryTo);
        editTextProType = (EditText) view.findViewById(R.id.editTextPropertyType);
        editTextProBedrooms = (EditText) view.findViewById(R.id.editTextBedrooms);
        editTextproFurnishing = (EditText) view.findViewById(R.id.editTextFurnishing);
        editTextproConstructionStatus = (EditText) view.findViewById(R.id.editTextConstructionstatus);
        editTextproListedBy = (EditText) view.findViewById(R.id.editTextListedBy);
        editTextproCarpetArea = (EditText) view.findViewById(R.id.editTextKarpetarea);
        editTextproProjectName = (EditText) view.findViewById(R.id.editTextParking);
        editTextproParkings = (EditText) view.findViewById(R.id.editTextProjectName);

        spinnerCategory = (Spinner) view.findViewById(R.id.spinnerCategory);

        layoutCarModel = (LinearLayout) view.findViewById(R.id.layoutCarMode);
        layoutCarYear = (LinearLayout) view.findViewById(R.id.layoutCarYear);
        layoutCarFuel = (LinearLayout) view.findViewById(R.id.layoutCarFuel);
        layoutCarKM = (LinearLayout) view.findViewById(R.id.layoutCarKMDriven);

         layoutbikeModel = (LinearLayout) view.findViewById(R.id.layoutbikeMode);
        layoutbikeYear = (LinearLayout) view.findViewById(R.id.layoutbikeYear);
        layoutbikeKM = (LinearLayout) view.findViewById(R.id.layoutbikeKMDriven);

        layoutSalPeriod = (LinearLayout) view.findViewById(R.id.layoutSalaryPeriod);
        layoutPositionType = (LinearLayout) view.findViewById(R.id.layoutPositionType);
        layoutSalFrom = (LinearLayout) view.findViewById(R.id.layoutSalaryFrom);
        layoutSalTo = (LinearLayout) view.findViewById(R.id.layoutSalaryTo);

        layoutProType = (LinearLayout) view.findViewById(R.id.layoutPropertyType);
        layoutProBedrooms = (LinearLayout) view.findViewById(R.id.layoutBedrooms);
        layoutproFurnishing = (LinearLayout) view.findViewById(R.id.layoutFurnishing);
        layoutproConstructionStatus = (LinearLayout) view.findViewById(R.id.layoutConstructionStatus);
        layoutproListedBy = (LinearLayout) view.findViewById(R.id.layoutLisedBy);
        layoutproCarpetArea = (LinearLayout) view.findViewById(R.id.layoutKarpetarea);
        layoutproProjectName = (LinearLayout) view.findViewById(R.id.layoutProjectName);
        layoutproParkings = (LinearLayout) view.findViewById(R.id.layoutParking);

        hidecarDetails();
        hidebikeDetails();
        hidejobDetails();

        recycleSubImages = (RecyclerView) view.findViewById(R.id.recycleSubImages);

        String[] recidential = new String[]{"Super Market", "My City", "Properties", "Bike",
                "Car", "Transport", "Travels", "Jobs", "Mobiles", "Agriculture", "Offers", "Others"};

        ArrayAdapter<String> spinnerArrayAdapterRecidential = new ArrayAdapter<String>(getContext(), R.layout.sppinner_layout_listitem, recidential);
        spinnerArrayAdapterRecidential.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerArrayAdapterRecidential);

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constant.DATABASE_PATH_UPLOADS);


        buttonChoose.setOnClickListener(this);
        buttonChooseSubImages.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String car = spinnerCategory.getSelectedItem().toString();
                if (car.equalsIgnoreCase("Car")) {
                    showcarDetails();
                } else {
                    hidecarDetails();
                }
                if (car.equalsIgnoreCase("Bike")) {
                    showbikeDetails();
                } else {
                    hidebikeDetails();
                }
                if (car.equalsIgnoreCase("Jobs")) {
                    showjobDetails();
                } else {
                    hidejobDetails();
                }
                if (car.equalsIgnoreCase("Properties")) {
                    showpropertyDetails();
                } else {
                    hidepropertyDetails();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data != null) {
                switch (requestCode) {
                    case REQUEST_PICK_IMAGE:

                        if (data.hasExtra("image_path")) {
                            Uri imagePath = Uri.parse(data.getStringExtra("image_path"));

                            String str = imagePath.toString();
                            String whatyouaresearching = str.substring(0, str.lastIndexOf("/"));
                            image = whatyouaresearching.substring(whatyouaresearching.lastIndexOf("/") + 1, whatyouaresearching.length());
                            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                            File file = new File(root, image);
                            filePath = Uri.fromFile(file);

                            setImage(filePath);
                        } else {
                            Toast.makeText(getContext(), "no image", Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case RESULT_LOAD_IMAGE:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (data.getClipData() != null) {

                                int totalItemsSelected = data.getClipData().getItemCount();

                                for (int i = 0; i < totalItemsSelected; i++) {

                                    Uri fileUri = data.getClipData().getItemAt(i).getUri();
                                    fileDoneList.add(data.getClipData().getItemAt(i).getUri());

                                    //String fileName = getFileName(fileUri);
                                }
                                productSubImagesAdapter = new Product_SubImages_Adapter(getActivity(), fileDoneList);
                                recycleSubImages.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                                recycleSubImages.setHasFixedSize(true);
                                recycleSubImages.setAdapter(productSubImagesAdapter);

                            } else if (data.getData() != null) {

                            }
                        }
                }
            }
        } else {

            System.out.println("Failed to load image");
        }

    }

    private void setImage(Uri imagePath) {

        imageView.setImageURI(imagePath);

    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            final StorageReference sRef = storageReference.child(Constant.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadurl1 = uri.toString();

                                    if (fileDoneList.size() != 0) {
                                        for (int i = 0; i < fileDoneList.size(); i++) {

                                            //getting the storage reference
                                            final StorageReference sRef = storageReference.child(Constant.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(fileDoneList.get(i)));

                                            //adding the file to reference
                                            sRef.putFile(fileDoneList.get(i))
                                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                            //dismissing the progress dialog
                                                            progressDialog.dismiss();

                                                            //displaying success toast
                                                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                @Override
                                                                public void onSuccess(Uri uri) {
                                                                    String downloadurl = uri.toString();
                                                                    fileDoneList1.add(downloadurl);
                                                                    if (fileDoneList.size() == fileDoneList1.size()) {

                                                                        Products product = fillUserModel("full");
                                                                        mDatabase.child(product.getProductId()).setValue(product);
                                                                        Toast.makeText(getContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                                                                        Intent intent = new Intent(getContext(), Main_Activity.class);
                                                                        startActivity(intent);
                                                                    }


                                                                }
                                                            });

                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception exception) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    })
                                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                            //displaying the upload progress
                                                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                                                        }
                                                    });
                                        }
                                    } else {
                                        Products product = fillUserModel("part");
                                        mDatabase.child(product.getProductId()).setValue(product);
                                        Toast.makeText(getContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getContext(), Main_Activity.class);
                                        startActivity(intent);
                                    }


                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
            Toast.makeText(getContext(), "Please Select a file", Toast.LENGTH_SHORT).show();
        }
    }

    private Products fillUserModel(String h) {

        Products product = new Products();
        String uploadId = mDatabase.push().getKey();
        String cat = spinnerCategory.getSelectedItem().toString();

        if (h.equalsIgnoreCase("full")) {

            product.setProductDescription(Idescription.getText().toString().trim());
            product.setProductName(editTextName.getText().toString().trim());
            product.setProductPrice(editTextPrice.getText().toString().trim());
            product.setProductCategory(spinnerCategory.getSelectedItem().toString());
            product.setUrl(downloadurl1);
            product.setSubImages(fileDoneList1);
            product.setProductId(uploadId);

            if (cat.equalsIgnoreCase("Car")) {
                if (!editTextCarModel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarModel(editTextCarModel.getText().toString());
                }
                if (!editTextCarFuel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarYear(editTextCarYear.getText().toString());
                }
                if (!editTextCarFuel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarFuel(editTextCarFuel.getText().toString());
                }
                if (!editTextCarKMDriven.getText().toString().equalsIgnoreCase("")) {
                    product.setCarKM(editTextCarKMDriven.getText().toString());
                }
            } else if (cat.equalsIgnoreCase("Bike")) {
                if (!editTextbikeModel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarModel(editTextbikeModel.getText().toString());
                }
                if (!editTextbikeYear.getText().toString().equalsIgnoreCase("")) {
                    product.setCarYear(editTextbikeYear.getText().toString());
                }
                if (!editTextbikeKMDriven.getText().toString().equalsIgnoreCase("")) {
                    product.setCarKM(editTextbikeKMDriven.getText().toString());
                }
            } else if (cat.equalsIgnoreCase("Jobs")) {
                product.setJobSalaryPeriod(editTextsalPeriod.getText().toString());
                product.setJobPosition(editTextPositionType.getText().toString());
                product.setJobSalaryFrom(editTextSalFrom.getText().toString());
                product.setJobSalaryTo(editTextSalTo.getText().toString());

            } else if (cat.equalsIgnoreCase("Properties")) {
                product.setPropertyType(editTextProType.getText().toString());
                product.setPropertyBedrooms(editTextProBedrooms.getText().toString());
                product.setPropertyFurnishing(editTextproFurnishing.getText().toString());
                product.setPropertyConstructionStatus(editTextproConstructionStatus.getText().toString());
                product.setPropertyListedBy(editTextproListedBy.getText().toString());
                product.setPropertyCarpetArea(editTextproCarpetArea.getText().toString());
                product.setPropertyProjectName(editTextproProjectName.getText().toString());
                product.setPropertyParkings(editTextproParkings.getText().toString());
            }

        } else if (h.equalsIgnoreCase("part")) {
            product.setProductDescription(Idescription.getText().toString().trim());
            product.setProductName(editTextName.getText().toString().trim());
            product.setProductPrice(editTextPrice.getText().toString().trim());
            product.setProductCategory(spinnerCategory.getSelectedItem().toString());
            product.setUrl(downloadurl1);
            product.setProductId(uploadId);

            if (cat.equalsIgnoreCase("Car")) {
                if (!editTextCarModel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarModel(editTextCarModel.getText().toString());
                }
                if (!editTextCarYear.getText().toString().equalsIgnoreCase("")) {
                    product.setCarYear(editTextCarYear.getText().toString());
                }
                if (!editTextCarFuel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarFuel(editTextCarFuel.getText().toString());
                }
                if (!editTextCarKMDriven.getText().toString().equalsIgnoreCase("")) {
                    product.setCarKM(editTextCarKMDriven.getText().toString());
                }
            } else if (cat.equalsIgnoreCase("Bike")) {
                if (!editTextbikeModel.getText().toString().equalsIgnoreCase("")) {
                    product.setCarModel(editTextbikeModel.getText().toString());
                }
                if (!editTextbikeYear.getText().toString().equalsIgnoreCase("")) {
                    product.setCarYear(editTextbikeYear.getText().toString());
                }
                if (!editTextbikeKMDriven.getText().toString().equalsIgnoreCase("")) {
                    product.setCarKM(editTextbikeKMDriven.getText().toString());
                }
            } else if (cat.equalsIgnoreCase("Jobs")) {
                product.setJobSalaryPeriod(editTextsalPeriod.getText().toString());
                product.setJobPosition(editTextPositionType.getText().toString());
                product.setJobSalaryFrom(editTextSalFrom.getText().toString());
                product.setJobSalaryTo(editTextSalTo.getText().toString());

            } else if (cat.equalsIgnoreCase("Properties")) {
                product.setPropertyType(editTextProType.getText().toString());
                product.setPropertyBedrooms(editTextProBedrooms.getText().toString());
                product.setPropertyFurnishing(editTextproFurnishing.getText().toString());
                product.setPropertyConstructionStatus(editTextproConstructionStatus.getText().toString());
                product.setPropertyListedBy(editTextproListedBy.getText().toString());
                product.setPropertyCarpetArea(editTextproCarpetArea.getText().toString());
                product.setPropertyProjectName(editTextproProjectName.getText().toString());
                product.setPropertyParkings(editTextproParkings.getText().toString());
            }


        }
        return product;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonChoose) {

            pickImage();

        } else if (view == buttonChooseSubImages) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
        } else if (view == buttonUpload) {

            String name = editTextName.getText().toString().trim();
            String DESC = Idescription.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(DESC)) {
                Toast.makeText(getContext(), "Enter Description!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (imageView.getDrawable() == null) {
                Toast.makeText(getContext(), "Image Required!", Toast.LENGTH_SHORT).show();
                return;
            }

            uploadFile();

        }
    }


    public void pickImage() {

        startActivityForResult(new Intent(getContext(), ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (permissions[0].equals(Manifest.permission.CAMERA) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        }
    }

    public void showcarDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutCarModel.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutCarYear.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutCarFuel.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutCarKM.getLayoutParams();
        params1.height = -1;
        params2.height = -1;
        params3.height = -1;
        params4.height = -1;
        layoutCarModel.setLayoutParams(params1);
        layoutCarYear.setLayoutParams(params2);
        layoutCarFuel.setLayoutParams(params3);
        layoutCarKM.setLayoutParams(params4);
    }

    public void hidecarDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutCarModel.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutCarYear.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutCarFuel.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutCarKM.getLayoutParams();
        params1.height = 0;
        params2.height = 0;
        params3.height = 0;
        params4.height = 0;
        layoutCarModel.setLayoutParams(params1);
        layoutCarYear.setLayoutParams(params2);
        layoutCarFuel.setLayoutParams(params3);
        layoutCarKM.setLayoutParams(params4);
    }

    public void showbikeDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutbikeModel.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutbikeYear.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutbikeKM.getLayoutParams();
        params1.height = -1;
        params2.height = -1;
        params4.height = -1;
        layoutbikeModel.setLayoutParams(params1);
        layoutbikeYear.setLayoutParams(params2);
        layoutbikeKM.setLayoutParams(params4);
    }

    public void hidebikeDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutbikeModel.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutbikeYear.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutbikeKM.getLayoutParams();
        params1.height = 0;
        params2.height = 0;
        params4.height = 0;
        layoutbikeModel.setLayoutParams(params1);
        layoutbikeYear.setLayoutParams(params2);
        layoutbikeKM.setLayoutParams(params4);
    }

    public void showjobDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutSalPeriod.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutPositionType.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutSalFrom.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutSalTo.getLayoutParams();
        params1.height = -1;
        params2.height = -1;
        params3.height = -1;
        params4.height = -1;
        layoutSalPeriod.setLayoutParams(params1);
        layoutPositionType.setLayoutParams(params2);
        layoutSalFrom.setLayoutParams(params3);
        layoutSalTo.setLayoutParams(params4);
    }

    public void hidejobDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutSalPeriod.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutPositionType.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutSalFrom.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutSalTo.getLayoutParams();
        params1.height = 0;
        params2.height = 0;
        params3.height = 0;
        params4.height = 0;
        layoutSalPeriod.setLayoutParams(params1);
        layoutPositionType.setLayoutParams(params2);
        layoutSalFrom.setLayoutParams(params3);
        layoutSalTo.setLayoutParams(params4);
    }

    public void showpropertyDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutProType.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutProBedrooms.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutproFurnishing.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutproConstructionStatus.getLayoutParams();
        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) layoutproListedBy.getLayoutParams();
        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) layoutproCarpetArea.getLayoutParams();
        LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) layoutproProjectName.getLayoutParams();
        LinearLayout.LayoutParams params8 = (LinearLayout.LayoutParams) layoutproParkings.getLayoutParams();
        params1.height = -1;
        params2.height = -1;
        params3.height = -1;
        params4.height = -1;
        params5.height = -1;
        params6.height = -1;
        params7.height = -1;
        params8.height = -1;
        layoutProType.setLayoutParams(params1);
        layoutProBedrooms.setLayoutParams(params2);
        layoutproFurnishing.setLayoutParams(params3);
        layoutproConstructionStatus.setLayoutParams(params4);
        layoutproListedBy.setLayoutParams(params5);
        layoutproCarpetArea.setLayoutParams(params6);
        layoutproProjectName.setLayoutParams(params7);
        layoutproParkings.setLayoutParams(params8);
    }

    public void hidepropertyDetails() {
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) layoutProType.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layoutProBedrooms.getLayoutParams();
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) layoutproFurnishing.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layoutproConstructionStatus.getLayoutParams();
        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) layoutproListedBy.getLayoutParams();
        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) layoutproCarpetArea.getLayoutParams();
        LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) layoutproProjectName.getLayoutParams();
        LinearLayout.LayoutParams params8 = (LinearLayout.LayoutParams) layoutproParkings.getLayoutParams();
        params1.height = 0;
        params2.height = 0;
        params3.height = 0;
        params4.height = 0;
        params5.height = 0;
        params6.height = 0;
        params7.height = 0;
        params8.height = 0;
        layoutProType.setLayoutParams(params1);
        layoutProBedrooms.setLayoutParams(params2);
        layoutproFurnishing.setLayoutParams(params3);
        layoutproConstructionStatus.setLayoutParams(params4);
        layoutproListedBy.setLayoutParams(params5);
        layoutproCarpetArea.setLayoutParams(params6);
        layoutproProjectName.setLayoutParams(params7);
        layoutproParkings.setLayoutParams(params8);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            // NOTE: This is the part that usually gives you the error
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
