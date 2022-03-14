import java.util.List;

class Manufacturer implements Runnable
{
    private final List<Car> manufacturedCars;
    private final int MAX_CAPACITY;

    public Manufacturer(List<Car> sharedResource, int size)
    {
        this.manufacturedCars = sharedResource;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                manufacture();
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void manufacture() throws InterruptedException
    {
        synchronized (manufacturedCars)
        {
            while (manufacturedCars.size() == MAX_CAPACITY)
            {
                System.out.println("Storage is full " + Thread.currentThread().getName() + " is waiting, size: " + manufacturedCars.size());
                manufacturedCars.wait();
            }

            Thread.sleep(1000);
            manufacturedCars.add(new Car());
            int id = manufacturedCars.size();
            System.out.println(Thread.currentThread().getName() + " manufactured a car # " + id);
            manufacturedCars.notifyAll();
        }
    }
}