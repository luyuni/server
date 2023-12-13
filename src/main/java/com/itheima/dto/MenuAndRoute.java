package com.itheima.dto;

import lombok.Data;
import sun.dc.pr.PRError;

import java.util.List;

@Data
public class MenuAndRoute {
    private List<Route> routeList;
    private List<Menu> menuTree;
    public MenuAndRoute(List<Route> routeList, List<Menu> menuTree) {
        this.routeList = routeList;
        this.menuTree = menuTree;
    }
}
