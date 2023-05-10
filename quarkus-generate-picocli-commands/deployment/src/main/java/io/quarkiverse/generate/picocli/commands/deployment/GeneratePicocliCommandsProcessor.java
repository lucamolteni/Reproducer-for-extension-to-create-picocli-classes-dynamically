package io.quarkiverse.generate.picocli.commands.deployment;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;

import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.AdditionalIndexedClassesBuildItem;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedClassBuildItem;
import io.quarkus.gizmo.ClassCreator;
import io.quarkus.gizmo.ClassOutput;
import io.quarkus.picocli.runtime.annotations.TopCommand;

class GeneratePicocliCommandsProcessor {

    private static final String FEATURE = "generate-picocli-command-extension";
    private final String TOP_COMMAND_NAME = "org.acme.ExampleTopCommand";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    BeanDefiningAnnotationBuildItem topcommandAnnotation() {
        return new BeanDefiningAnnotationBuildItem(DotName.createSimple(TopCommand.class.getName()));
    }

    @BuildStep
    protected void build(
            ApplicationIndexBuildItem applicationIndex,
            BuildProducer<GeneratedClassBuildItem> generatedBean,
            BuildProducer<AdditionalIndexedClassesBuildItem> additionalClasses) {

        System.out.println("++++ Init: " + TOP_COMMAND_NAME);

        ClassOutput classOutput = (name, data) -> {
            System.out.println("Generating classoutput: " + name);
            generatedBean.produce(new GeneratedClassBuildItem(true, name, data));
            try {
                Indexer indexer = new Indexer();
                indexer.index(new ByteArrayInputStream(data));
                Index indexWithBuiltClass = indexer.complete();

                System.out.println(indexWithBuiltClass.getClassByName(TOP_COMMAND_NAME));
                additionalClasses.produce(new AdditionalIndexedClassesBuildItem(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        ClassCreator classCreator = ClassCreator.builder()
                .classOutput(classOutput)
                .className(TOP_COMMAND_NAME)
                .build();

        classCreator.addAnnotation(io.quarkus.picocli.runtime.annotations.TopCommand.class);

        classCreator.close();
    }

}
