package framework.actors;

public class Factory {
    public static Actor make(ActorType type) {
        //noinspection CommentedOutCode
        switch (type) {
            case CHROME:
                return new ChromeActor();
            case FIREFOX:
                return new FirefoxActor();
            //                case INTERNET_EXPLORER:
            //                IE web driver needs more work. It does not wait for page loads and crashes in various ways
            //                    myHandle = new IeActor();
            //                    break;
            //                case SAFARI:
            //                    break;
            //                case ANDROID:
            //                    myHandle = new AndroidActor();
            //                    break;
            //                case IOS:
            //                    break;
            //                case API:
            //                    myHandle = new RestActor();
            //                    break;
        }
        System.out.println("[ERROR] Can't create an actor of type " + type + ":");
        return null;
    }
}
