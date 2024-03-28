package com.nzy.plugin;

import com.android.build.gradle.AppExtension;
import com.nzy.plugin.time.LoggerUtil;
import com.nzy.plugin.time.SoTransform;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionGraph;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;

import java.util.List;

/**
 * @author momo
 */
public class SoPlugin implements Plugin<Project> {

    private String TAG = "TimePlugin";
    private Project mProject;
    private Logger mLogger;
    private AppExtension mAppExtension;

    @Override
    public void apply(Project project) {
        System.out.println("-------NzyPlugin------");
        // 注册 Transform, AppExtension 依赖 gradle，所以该模块需要导入 gradle
        mAppExtension = project.getExtensions().getByType(AppExtension.class);
        LoggerUtil.setLog(project.getLogger());
        // 打印每个方法的时间
        mAppExtension.registerTransform(new SoTransform(project));
        mProject = project;
        printAllTask();
        mLogger = project.getLogger();
    }

    private void printAllTask() {
        mProject.getGradle().getTaskGraph().whenReady(new Action<TaskExecutionGraph>() {
            @Override
            public void execute(TaskExecutionGraph taskGraph) {
                List<Task> allTasks = taskGraph.getAllTasks();
                for (int i = 0; i < allTasks.size(); i++) {
                    Task task = allTasks.get(i);
                    //打印出所有的task的名字
                    mLogger.log(LogLevel.ERROR, TAG + "所有的task ：" + i + "-----" + task.getName());
                }

                mLogger.log(LogLevel.ERROR, TAG + " mProject.whenReady");
            }
        });

        mProject.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                mLogger.log(LogLevel.ERROR, TAG + " mProject.afterEvaluate");
//                initConfig();
            }
        });

    }


}
