package ru.netcracker.registration.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class TaskDTO {
    @JsonProperty("task")
    private String task;
    @JsonProperty("execution")
    private DateTime execution;
    @JsonProperty("option")
    private String option;
    @JsonProperty("finished")
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (!task.equals(taskDTO.task)) return false;
        if (!execution.equals(taskDTO.execution)) return false;
        if (!option.equals(taskDTO.option)) return false;
        return finished.equals(taskDTO.finished);
    }

    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + execution.hashCode();
        result = 31 * result + option.hashCode();
        result = 31 * result + finished.hashCode();
        return result;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public DateTime getExecution() {
        return execution;
    }

    public void setExecution(DateTime execution) {
        this.execution = execution;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
