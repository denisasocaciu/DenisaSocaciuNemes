package JDBC;

import java.util.Objects;

public class Accomodation {
    private int id;
    private String type;
    private String bedType;
    private int maxGuests;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accomodation that = (Accomodation) o;
        return id == that.id &&
                maxGuests == that.maxGuests &&
                type.equals(that.type) &&
                bedType.equals(that.bedType) &&
                description.equals(that.description);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, type, bedType, maxGuests, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Accomodation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bedType='" + bedType + '\'' +
                ", maxGuests=" + maxGuests +
                ", description='" + description + '\'' +
                '}';


    }
}
