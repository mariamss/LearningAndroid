package ge.gamp.learningandroid.data.repository;

import java.util.List;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

public class CombinedRepository implements Repository{
    private static CombinedRepository INSTANCE;
    private FakeRestRepository restRepository;
    private InMemoryRepository inMemoryRepository;

    private CombinedRepository(){
        restRepository = new FakeRestRepository();
        inMemoryRepository = new InMemoryRepository();
    };

    public static CombinedRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CombinedRepository();
        }

        return INSTANCE;
    }


    @Override
    public void getProgrammers(ResponseHandler<List<Programmer>> handler) {
        // ToDO: Change this to work with both repositories
        restRepository.getProgrammers(handler);
    }

    @Override
    public void getProgrammer(int id, ResponseHandler<Programmer> handler) {
        inMemoryRepository.getProgrammer(id, response -> {
            if(response == null){
                restRepository.getProgrammer(id, handler);
            }else{
                handler.handleResponse(response);
            }
        });
    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {
        // ToDO: Change this to work with both repositories
        restRepository.deleteProgrammer(id, handler);
    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {
        // ToDO: Change this to work with both repositories
        restRepository.createProgrammer(programmer, handler);
    }
}
