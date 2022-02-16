package cz.cvut.fit.tjv.fittour.fittourclient.model;

public class ModelConverter
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

    public static RiderModel createModelWtihNoIDs(RiderModel riderModel)
    {
        return new RiderModel(null,
                riderModel.getName(),
                riderModel.getSurname(),
                riderModel.getDateOfBirth(),
                null);
    }
}
