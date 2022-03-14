import java.util.List;

class Buyer implements Runnable
{
    private final List<Car> manufacturedCars;
    private int carsToBuy;

    public Buyer(List<Car> sharedResource, int numberCarsToBuy)
    {
        this.manufacturedCars = sharedResource;
        this.carsToBuy = numberCarsToBuy;
    }

    @Override
    public void run()
    {
        //while (true)
        while (carsToBuy>0)
        {
            try
            {
                buy();
                carsToBuy--;
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void buy() throws InterruptedException
    {
        synchronized (manufacturedCars)
        {
            while (manufacturedCars.isEmpty())
            //
            // while (manufacturedCars.size() < carsToBuy)
            {
                System.out.println("No cars at the moment, " + Thread.currentThread().getName() + " is waiting, size: " + manufacturedCars.size());
                manufacturedCars.wait();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "buy a car # " + manufacturedCars.size());

            manufacturedCars.remove(0);
            manufacturedCars.notifyAll();
        }
    }
}