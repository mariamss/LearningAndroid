package ge.gamp.learningandroid.data.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

class InMemoryRepository implements Repository{

    private List<Programmer> programmers = new ArrayList<>();

    @Override
    public void getProgrammers(ResponseHandler<List<Programmer>> handler) {
        handler.handleResponse(this.programmers);
    }

    @Override
    public void getProgrammer(int id, ResponseHandler<Programmer> handler) {
        Optional<Programmer> foundProgrammer = programmers.stream()
                .filter(programmer -> programmer.getId() == id)
                .findAny();
        handler.handleResponse(foundProgrammer.orElse(null));
    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {
        programmers.removeIf(programmer -> programmer.getId() == id);
        handler.handleResponse(true);
    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {
        this.programmers.add(programmer);
        handler.handleResponse(true);

    }

    @Override
    public void setProgrammers(List<Programmer> programmers) {
        this.programmers.addAll(programmers);
    }

    @Override
    public void addProgrammer(Programmer programmer) {
        this.programmers.add(programmer);
    }

}
