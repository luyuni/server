package com.itheima.service;

import com.itheima.dto.Menu;
import com.itheima.dto.MenuAndRoute;
import com.itheima.dto.Route;
import com.itheima.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public MenuAndRoute findAll() {
        List<Menu> list = menuMapper.findAll();
        return menu2MenuAndRoute(list);
    }

    public MenuAndRoute findByUser(String username) {
        List<Menu> list = menuMapper.findByUser(username);
        return menu2MenuAndRoute(list);
    }

    private MenuAndRoute menu2MenuAndRoute(List<Menu> list) {
        Map<Integer, Menu> all = new HashMap<>();
        List<Menu> tree = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        for (Menu menu : list) {
            if (menu.getPid() == 0) {
                tree.add(menu);
            }
            all.put(menu.getId(), menu);
            if (menu.getRoutePath() != null) {
                routes.add(new Route(menu.getRoutePath(), menu.getRouteElement()));
            }
        }

        for (Menu menu : list) {
            Menu parent = all.get(menu.getPid());
            if (parent != null) {
                List<Menu> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(menu);
            }
        }
        return new MenuAndRoute(routes, tree);
    }
}
