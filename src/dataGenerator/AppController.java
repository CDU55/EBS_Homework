package dataGenerator;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import dataGenerator.generators.PublicationsGenerator;
import dataGenerator.generators.SubscriptionsAssignmentGenerator;
import dataGenerator.generators.SubscriptionsGenerator;
import dataGenerator.models.Field;
import dataGenerator.models.InputModel;
import dataGenerator.models.Publication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    @FXML
    private TextArea console;

    private String inputPath;
    private String outputPath;


    public void generate()
    {
        console.setText("");
        if(this.inputPath==null)
        {
            console.setStyle("-fx-text-fill: red ;") ;
            console.setText("Please choose an input file");
        }
        else
        {
            if(this.outputPath==null)
            {
                console.setStyle("-fx-text-fill: red ;") ;
                console.setText("Please choose and output file");
            }
            else
            {
                PublicationsGenerator publicationsGenerator=new PublicationsGenerator();
                InputModel inputData=Utils.readInput(this.inputPath,publicationsGenerator);
                List<Publication> publications=publicationsGenerator.generatePublications(inputData.publicationsNumber);
                if(publications==null)
                {
                    console.setStyle("-fx-text-fill: red ;") ;
                    console.setText("ERROR : Could not generate publications");
                    return;
                }
                int[] fieldsSubscriptionsCount=new int[inputData.fields.size()];
                for(int fieldIndex=0;fieldIndex<fieldsSubscriptionsCount.length;fieldIndex++)
                {
                    Field field=inputData.fields.get(fieldIndex);
                    int fieldSubscriptionCount=Utils.calculatePercentage(inputData.subscriptionsNumber,field.frequency);
                    if(fieldSubscriptionCount==-1)
                    {
                        console.setStyle("-fx-text-fill: red ;") ;
                        console.setText("ERROR : An invalid sequence was set for "+field.name);
                        return;
                    }
                    fieldsSubscriptionsCount[fieldIndex]=fieldSubscriptionCount;
                }
                SubscriptionsAssignmentGenerator assignmentGenerator=new SubscriptionsAssignmentGenerator();
                int[][] subscriptionAssignment=assignmentGenerator.getAssignment(inputData.fields.size(),inputData.subscriptionsNumber,fieldsSubscriptionsCount);
                if(subscriptionAssignment==null)
                {
                    console.setStyle("-fx-text-fill: red ;") ;
                    console.setText("ERROR : Could not generate subscription field assignation, please check the frequency for each field");
                    return;
                }
                SubscriptionsGenerator subscriptionsGenerator=new SubscriptionsGenerator();
                List<String> subscriptions=subscriptionsGenerator.generateSubscriptions(subscriptionAssignment,inputData.fields);
                if(subscriptions==null)
                {
                    console.setStyle("-fx-text-fill: red ;") ;
                    console.setText("ERROR : Invalid equal operator frequency for one or more fields");
                    return;
                }
                List<String> publicationsString=new ArrayList<String>();
                for(Publication publication:publications)
                {
                    publicationsString.add(publication.toString());
                }
                boolean writeSuccess=Utils.writeOutput(this.outputPath,publicationsString,subscriptions);
                if(writeSuccess)
                {
                    console.setStyle("-fx-text-fill: green ;") ;
                    console.setText("The output was saved at "+this.outputPath);
                }
                else
                {
                    console.setStyle("-fx-text-fill: red ;") ;
                    console.setText("ERROR : An error occurred");
                }
            }
        }
    }


    public void chooseInputPath()
    {
            FileChooser chooser=new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files","*.json"));
            File f=chooser.showOpenDialog(null);
            if(f!=null)
            {
                this.inputPath=f.getAbsolutePath();
            }
    }

    public void chooseOutputPath()
    {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files","*.json"));
        File f=chooser.showOpenDialog(null);
        if(f!=null)
        {
            this.outputPath=f.getAbsolutePath();
        }
    }

}
