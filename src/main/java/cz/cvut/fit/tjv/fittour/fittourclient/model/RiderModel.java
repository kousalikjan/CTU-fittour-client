package cz.cvut.fit.tjv.fittour.fittourclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

public class RiderModel
{
    public Integer id;

    public String name;

    public String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateOfBirth;

    public SnowboardDto snowboard;

    //public Collection<ContestDto> contests;

    public RiderModel()
    {
    }

    public RiderModel(RiderModel riderModel)
    {
        this.id = riderModel.getId();
        this.name = riderModel.getName();
        this.surname = riderModel.getSurname();
        this.dateOfBirth = riderModel.getDateOfBirth();
        this.snowboard = riderModel.getSnowboard();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public SnowboardDto getSnowboard()
    {
        return snowboard;
    }

    public void setSnowboard(SnowboardDto snowboard)
    {
        this.snowboard = snowboard;
    }

}
