package cz.cvut.fit.tjv.fittour.fittourclient.model;

public class SnowboardModel extends SnowboardDto
{
    private boolean error;

    public SnowboardModel()
    {

    }

    public SnowboardModel(boolean error, SnowboardDto snowboardDto)
    {
        super(snowboardDto.getId(),
                snowboardDto.getBrand(),
                snowboardDto.getModelName(),
                snowboardDto.getProfile(),
                snowboardDto.getFlex(),
                snowboardDto.getPrice());
        this.error = error;
    }

    public SnowboardModel(Integer id, String brand, String modelName, String profile, int flex, int price, boolean error)
    {
        super(id, brand, modelName, profile, flex, price);
        this.error = error;
    }

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }
}
