import java.util.ArrayList;
import java.util.List;

interface NewsObserver {
  void update(String News);
}

interface NewsSubject {
  void registerObserver(NewsObserver observer);

  void removeObserver(NewsObserver observer);

  void notifyObservers(String News);
}

class NewsAgency implements NewsSubject {
  private List<NewsObserver> observers = new ArrayList<>();

  @Override
  public void registerObserver(NewsObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(NewsObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String News) {
    for (NewsObserver observer : observers) {
      observer.update(News);
    }
  }

  public void broadcastNews(String News) {
    System.out.println("Breaking News: " + News);
    notifyObservers(News);
  }
}

class NewsChannel implements NewsObserver {
  private String channelName;

  public NewsChannel(String channelName) {
    this.channelName = channelName;
  }

  @Override
  public void update(String News) {
    System.out.println(channelName + " received News update: " + News);
  }
}

public class NewsAgencyImplementation {
  public static void main(String[] args) {
    NewsAgency Agency = new NewsAgency();

    NewsChannel channelA = new NewsChannel("NewsChannel A");
    NewsChannel channelB = new NewsChannel("NewsChannel B");
    NewsChannel channelC = new NewsChannel("NewsChannel C");

    Agency.registerObserver(channelA);
    Agency.registerObserver(channelB);
    Agency.registerObserver(channelC);

    Agency.broadcastNews("Pakistan Wins WorldCup");

    Agency.removeObserver(channelB);

    Agency.broadcastNews("Heavy Rainstorm expected in Lahore");
  }
}