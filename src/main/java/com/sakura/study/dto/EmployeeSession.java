package com.sakura.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.websocket.Session;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeSession {

    private Integer employeeId;

    private Session session;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeSession that = (EmployeeSession) o;

        if (!employeeId.equals(that.employeeId)) return false;
        return session.equals(that.session);
    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
        result = 31 * result + session.hashCode();
        return result;
    }
}
