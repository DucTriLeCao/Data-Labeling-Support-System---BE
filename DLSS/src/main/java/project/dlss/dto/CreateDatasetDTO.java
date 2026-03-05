public class CreateDatasetDTO {

    private long projectId;
    private String name;
    private String description;

    public long getProjectId(){return projectId;}
    public void setProjectId(long projectId){this.projectId=projectId;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
}