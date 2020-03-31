import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.ModelVersion;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;
import clarifai2.exception.ClarifaiException;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.List;

public class Program {
    void output(List<ClarifaiOutput<Concept>> response){
        for (int i = 0;i<response.size();i++){
            for (int j = 0;j<response.get(i).data().size();j++){
                System.out.println(response.get(i).data().get(j).name());
            }
        }
    }


    public static void main(String[] args) {

        ClarifaiClient client = new ClarifaiBuilder("0444ae2aa58342d9a867e30bafe393b3")
                .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync();

        final List<ClarifaiOutput<Concept>> response = client.getDefaultModels().generalModel().predict()
                .withInputs(ClarifaiInput.forImage("https://media.wired.com/photos/5d09594a62bcb0c9752779d9/125:94/w_1994,h_1500,c_limit/Transpo_G70_TA-518126.jpg"))
                .executeSync().get();

        /*ConceptModel model = client.getDefaultModels().generalModel();
        ModelVersion modelVersion = model.getVersionByID("c0c0ac362b03416da06ab3fa36fb58e3").executeSync().get();

        ClarifaiResponse<List<ClarifaiOutput<Prediction>>> response1 = client.predict(model.id())
                .withInputs(ClarifaiInput.forImage("some-URL"))
                .withVersion(modelVersion)
                .executeSync();*/
        Program program = new Program();
        program.output(response);
    }
}
