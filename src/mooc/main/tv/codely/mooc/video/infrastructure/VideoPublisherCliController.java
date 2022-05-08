package tv.codely.mooc.video.infrastructure;

import tv.codely.mooc.notification.application.create.SendPushToSubscribersOnVideoPublished;
import tv.codely.mooc.video.application.publish.VideoPublisher;
import tv.codely.mooc.video.domain.port.VideoManagementPort;
import tv.codely.mooc.video.infrastructure.adapter.MetaVideoManagementAdapter;
import tv.codely.shared.application.DomainEventSubscriber;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.infrastructure.bus.ReactorEventBus;

import java.util.Set;

public class VideoPublisherCliController {
    public static void main(String[] args) {
        final Set<DomainEventSubscriber> subscribers = Set.of(
            new SendPushToSubscribersOnVideoPublished()
        );
        final EventBus eventBus = new ReactorEventBus(subscribers);
        final VideoManagementPort videoManagementPort = new MetaVideoManagementAdapter();
        final var videoPublisher = new VideoPublisher(eventBus, videoManagementPort);

        final var videoTitle = "\uD83C\uDF89 New YouTube.com/CodelyTV video title";
        final var videoDescription = "This should be the video description \uD83D\uDE42";

        videoPublisher.publish(videoTitle, videoDescription);
    }
}
