package ge.gamp.learningandroid.data.repository;
import android.util.Log;

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
        inMemoryRepository.getProgrammers(inMemoryProgrammer -> {
            if(inMemoryProgrammer.size()==0){
                restRepository.getProgrammers(restProgrammers -> {
                    setProgrammers(restProgrammers);
                    handler.handleResponse(restProgrammers);
                });
            }else{
                handler.handleResponse(inMemoryProgrammer);
            }
        });
    }

    @Override
    public void getProgrammer(int id, ResponseHandler<Programmer> handler) {
        inMemoryRepository.getProgrammer(id, response -> {
            if(response == null){
                restRepository.getProgrammer(id, currentProgrammer->{
                    handler.handleResponse(currentProgrammer);
                });
            }else{
                handler.handleResponse(response);
            }
        });
    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {
        restRepository.deleteProgrammer(id, (result) -> {
            inMemoryRepository.deleteProgrammer(id, handler);
            handler.handleResponse(result);
        });
    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {
        restRepository.createProgrammer(programmer, result -> {
            addProgrammer(programmer);
            handler.handleResponse(result);
        });
    }

    @Override
    public void setProgrammers(List<Programmer> programmers) {
        inMemoryRepository.setProgrammers(programmers);
    }

    @Override
    public void addProgrammer(Programmer programmer) {
        inMemoryRepository.addProgrammer(programmer);
    }
}
