package healthsparq;

/**
 * A class for storing Pet data.
 * Id fields usually are auto-generated (numeric or UUID),
 * but in this case all fields are plain strings for consistency reasons.
 */
public class Pet {
    private String id;
    private String name;
    private String type;
    private String gender;
    private String zipCode;

    public Pet() {
    }

    public Pet(String id, String name, String type, String gender, String zipCode) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
