package ge.gamp.learningandroid.data.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ge.gamp.learningandroid.data.ResponseHandler;
import ge.gamp.learningandroid.data.model.Programmer;

class FakeRestRepository implements Repository{
    private final List<Programmer> programmers = new ArrayList<>();
    public FakeRestRepository(){
        programmers.add(new Programmer(1, "Gurama", "java", 23, 1200, "bio", "https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png"));
        programmers.add(new Programmer(2, "Tengiza", "java", 21, 1500, "bio", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS0xzH6x260xclX7ayT1E6JfjymTPSVmMDXxPT395_r6Q5sFGyh&usqp=CAU"));
        programmers.add(new Programmer(3, "Didgora", "java", 25, 1121, "bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
        programmers.add(new Programmer(4, "Guaranduxt", "java", 27, 931, "bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
        programmers.add(new Programmer(5, "Mzevinari", "java", 29, 800, "bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
        programmers.add(new Programmer(6, "ბუხუტი", "java", 21, 700, "bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
        programmers.add(new Programmer(7, "Mariami", "java", 18, 150, "bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
        programmers.add(new Programmer(8, "Mendoza", "java", 17, 11500,"bio", "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803"));
    }

    @Override
    public void getProgrammers(final ResponseHandler<List<Programmer>> handler) {
        new Thread(() -> {
            try {
                Thread.sleep(1500);

                new Handler(Looper.getMainLooper()).post(() -> handler.handleResponse(new ArrayList<>(programmers)));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void getProgrammer(final int id, ResponseHandler<Programmer> handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    Optional<Programmer> foundProgrammer = programmers.stream()
                            .filter(programmer -> programmer.getId() == id)
                            .findAny();

                    new Handler(Looper.getMainLooper()).post(() -> foundProgrammer.orElse(null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void deleteProgrammer(int id, ResponseHandler<Boolean> handler) {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                programmers.removeIf(programmer -> programmer.getId() == id);

                new Handler(Looper.getMainLooper()).post(() -> handler.handleResponse(true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void createProgrammer(Programmer programmer, ResponseHandler<Boolean> handler) {
        this.programmers.add(programmer);

        new Handler(Looper.getMainLooper()).post(() -> handler.handleResponse(true));
    }

    @Override
    public void setProgrammers(List<Programmer> programmers) {

    }

    @Override
    public void addProgrammer(Programmer programmer) {

    }


//    @Override
//    public void updateProgrammer(Programmer programmer, ResponseHandler<Programmer> handler) {
//        new Thread(() -> {
//            try {
//                Thread.sleep(1500);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
}
