package cz.cvut.fit.tjv.fittour.fittourclient.model;

public class SnowboardConverter
{

    public static SnowboardDto createDtoWtihNoID(SnowboardDto snowboardDto)
    {
        return new SnowboardDto(null,
                snowboardDto.getBrand(),
                snowboardDto.getModelName(),
                snowboardDto.getProfile(),
                snowboardDto.getFlex(),
                snowboardDto.getPrice());
    }
}
